package com.david0926.mbit.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.david0926.mbit.R
import com.david0926.mbit.data.auth.UpdateInfoRequest
import com.david0926.mbit.databinding.ActivityEditProfileBinding
import com.david0926.mbit.network.auth.AuthManager
import com.david0926.mbit.ui.dialog.LoadingDialog
import com.david0926.mbit.ui.login.LoginActivity
import com.david0926.mbit.util.UserCache
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import gun0912.tedbottompicker.TedBottomPicker
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class EditProfileActivity : AppCompatActivity() {

    lateinit var viewModel: EditProfileActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityEditProfileBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_edit_profile)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(EditProfileActivityViewModel::class.java)
        binding.viewModel = viewModel

        val user = UserCache.getUser(this)
        viewModel.user = user
        viewModel.username.value = user.username
        viewModel.personality.value = user.personalityType

        binding.spinnerEditProfile.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.personality.value =
                        resources.getStringArray(R.array.personality)[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }

        binding.btnEditProfileBack.setOnClickListener { finish() }

        binding.btnEditProfileProfile.setOnClickListener { selectProfile() }
        binding.imgEditProfileProfile.setOnClickListener { selectProfile() }

        binding.btnEditProfileSave.setOnClickListener {

            val dialog = LoadingDialog(this)
            dialog.setMessage("프로필 수정 중...").show()

            var photoBody: MultipartBody.Part? = null
            if (viewModel.profile.value != null) {
                val photoFile = File(viewModel.profile.value!!.path!!)
                val photoFileBody =
                    RequestBody.create(MediaType.parse("multipart/form-data"), photoFile)
                photoBody =
                    MultipartBody.Part.createFormData("photo", photoFile.name, photoFileBody)
            }

            val authManager = AuthManager()
            authManager.setUserData(
                UserCache.getToken(this),
                UpdateInfoRequest(
                    viewModel.username.value,
                    if (viewModel.user.personalityType == viewModel.personality.value) null
                    else viewModel.personality.value,
                    null,
                    null,
                    photoBody,
                    false
                ),
                onResponse = { commonResponse, userModel ->
                    Log.d("baam", "onCreate: " + commonResponse.message)
                    if (commonResponse.status != 200) {
                        dialog.cancel()
                        viewModel.errorMsg.value = commonResponse.message
                        return@setUserData
                    }
                    dialog.success("프로필 수정 완료!", onSuccess = {
                        finishAffinity()
                        startActivity(Intent(this, LoginActivity::class.java))
                    })
                },
                onFailure = {
                    it.printStackTrace()
                    dialog.cancel()
                    Toast.makeText(this, "프로필 수정에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

    private fun selectProfile() {
        TedPermission
            .with(this)
            .setPermissions(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .setPermissionListener(object : PermissionListener {
                override fun onPermissionGranted() {
                    TedBottomPicker.with(this@EditProfileActivity)
                        .show { viewModel.profile.value = it }
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {}
            })
            .check()
    }
}
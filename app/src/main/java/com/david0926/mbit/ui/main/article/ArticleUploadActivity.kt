package com.david0926.mbit.ui.main.article

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.david0926.mbit.R
import com.david0926.mbit.data.post.PostCreateRequest
import com.david0926.mbit.databinding.ActivityArticleUploadBinding
import com.david0926.mbit.network.post.PostManager
import com.david0926.mbit.ui.dialog.LoadingDialog
import com.david0926.mbit.util.UserCache
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import gun0912.tedbottompicker.TedBottomPicker
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class ArticleUploadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityArticleUploadBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_article_upload)
        binding.lifecycleOwner = this

        val viewModel = ViewModelProvider(this).get(ArticleUploadActivityViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.private.value = intent.getBooleanExtra("private", false)

        binding.btnArticleUploadImage.setOnClickListener {
            TedPermission
                .with(this)
                .setPermissions(
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                .setPermissionListener(object : PermissionListener {
                    override fun onPermissionGranted() {
                        TedBottomPicker.with(this@ArticleUploadActivity)
                            .show { viewModel.photo.value = it }
                    }

                    override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {}
                })
                .check()
        }

        binding.btnArticleUploadUpload.setOnClickListener {

            val dialog = LoadingDialog(this)
            dialog.setMessage("업로드 중...").show()

            var photoBody: MultipartBody.Part? = null

            if (viewModel.photo.value != null) {
                val photoFile = File(viewModel.photo.value!!.path!!)
                val photoFileBody =
                    RequestBody.create(MediaType.parse("multipart/form-data"), photoFile)
                photoBody =
                    MultipartBody.Part.createFormData("photo", photoFile.name, photoFileBody)
            }

            val postManager = PostManager()
            postManager.createPost(
                UserCache.getToken(this),
                PostCreateRequest(viewModel.text.value!!, viewModel.private.value!!, photoBody),
                onResponse = {
                    dialog.cancel()
                    if (it.status != 200) {
                        dialog.cancel()
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    }
                    Log.d("baam", "onCreate: " + it.message)
                    finish()
                },
                onFailure = {
                    dialog.cancel()
                    Toast.makeText(this, "게시물 업로드에 실패했습니다.", Toast.LENGTH_SHORT).show()
                    it.printStackTrace()
                }
            )
        }

        binding.btnArticleUploadBack.setOnClickListener { onBackPressed() }
    }
}
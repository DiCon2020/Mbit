package com.david0926.mbit.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.david0926.mbit.R
import com.david0926.mbit.data.auth.LoginRequest
import com.david0926.mbit.data.auth.RegisterRequest
import com.david0926.mbit.databinding.FragmentRegister2Binding
import com.david0926.mbit.network.auth.AuthManager
import com.david0926.mbit.ui.dialog.LoadingDialog
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import gun0912.tedbottompicker.TedBottomPicker
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class Register2Fragment : Fragment() {

    lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentRegister2Binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_register_2, container, false
        )
        binding.lifecycleOwner = requireActivity()

        viewModel = ViewModelProvider(requireActivity()).get(RegisterViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.onNextClick = {
            val dialog = LoadingDialog(requireActivity())
            dialog.setMessage("회원가입 중...").show()

            val authManager = AuthManager()

            val photoBody: MultipartBody.Part?
            val photoFile = File(viewModel.profile.value!!.path!!)
            val photoFileBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), photoFile)
            photoBody = MultipartBody.Part.createFormData("photo", photoFile.name, photoFileBody)

            val registerRequest = RegisterRequest(
                viewModel.email.value!!,
                viewModel.pw.value!!,
                viewModel.name.value!!,
                viewModel.birth.value!!.toInt(),
                null,
                photoBody
            )

            authManager.register(
                registerRequest, onResponse = {
                    if (it.status != 200) {
                        dialog.cancel()
                        viewModel.errorMsg.value = it.message
                        return@register
                    }
                    dialog.setMessage("유저 정보 동기화중...")
                    authManager.login(LoginRequest(registerRequest.id, registerRequest.password),
                        onResponse = { response, data ->
                            dialog.cancel()
                            if (response.status != 200) {
                                viewModel.errorMsg.value = it.message
                                return@login
                            }
                            viewModel.token.value = response.accessToken
                            viewModel.user.value  = data
                            viewModel.nextPage()
                        },
                        onFailure = { t ->
                            dialog.cancel()
                            viewModel.errorMsg.value = "유저 정보 동기화에 실패했습니다."
                            t.printStackTrace()
                        })
                }, onFailure = {
                    dialog.cancel()
                    viewModel.errorMsg.value = "회원가입 요청에 실패했습니다."
                    it.printStackTrace()
                })
        }

        viewModel.profile.observe(viewLifecycleOwner, { checkNextEnabled() })
        viewModel.name.observe(viewLifecycleOwner, { checkNextEnabled() })
        viewModel.birth.observe(viewLifecycleOwner, { checkNextEnabled() })
        viewModel.policy.observe(viewLifecycleOwner, { checkNextEnabled() })

        binding.btnRegister2Profile.setOnClickListener { selectProfile() }
        binding.imgRegister2Profile.setOnClickListener { selectProfile() }

        binding.btnRegister2Next.setOnClickListener { viewModel.onNextClick.invoke() }

        return binding.root
    }

    private fun selectProfile() {
        TedPermission
            .with(requireContext())
            .setPermissions(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .setPermissionListener(object : PermissionListener {
                override fun onPermissionGranted() {
                    TedBottomPicker.with(requireActivity()).show { viewModel.profile.value = it }
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {}
            })
            .check()
    }

    private fun checkNextEnabled() {
        viewModel.nextEnabled.value =
            viewModel.profile.value != null && viewModel.name.value!!.trim().isNotEmpty()
                    && viewModel.birth.value!!.length > 3 && viewModel.policy.value!!
    }
}
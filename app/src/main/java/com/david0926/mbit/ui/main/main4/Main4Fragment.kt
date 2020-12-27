package com.david0926.mbit.ui.main.main4

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.david0926.mbit.BuildConfig
import com.david0926.mbit.R
import com.david0926.mbit.data.auth.UpdateInfoRequest
import com.david0926.mbit.databinding.FragmentMain4Binding
import com.david0926.mbit.network.auth.AuthManager
import com.david0926.mbit.ui.login.LoginActivity
import com.david0926.mbit.ui.notification.NotiActivity
import com.david0926.mbit.ui.profile.EditProfileActivity
import com.david0926.mbit.util.UserCache

class Main4Fragment : Fragment() {

    lateinit var viewModel: Main4FragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentMain4Binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main_4, container, false
        )
        binding.lifecycleOwner = requireActivity()

        viewModel = ViewModelProvider(requireActivity()).get(Main4FragmentViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.version.value = BuildConfig.VERSION_CODE.toDouble().toString()

        binding.btnMain4Logout.setOnClickListener {
            UserCache.clearUser(requireContext())
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        }

        binding.btnMain4EditProfile.setOnClickListener {
            startActivity(Intent(requireContext(), EditProfileActivity::class.java))
        }

        viewModel.topicChecked.observe(viewLifecycleOwner, { checkChange() })
        viewModel.commentChecked.observe(viewLifecycleOwner, { checkChange() })

        binding.btnMain4Delete.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext(), R.style.AlertDialogTheme)
            builder.setMessage("정말로 탈퇴할까요? 탈퇴한 계정은 복구할 수 없습니다.")
            builder.setPositiveButton("탈퇴") { _, _ ->
                val authManager = AuthManager()
                authManager.deleteUser(UserCache.getToken(requireContext()),
                    onResponse = {
                        startActivity(Intent(requireContext(), LoginActivity::class.java))
                        requireActivity().finish()
                    },
                    onFailure = {
                        it.printStackTrace()
                        Toast.makeText(requireContext(), "회원 탈퇴에 실패했습니다.", Toast.LENGTH_SHORT)
                            .show()
                    })
            }.setNegativeButton("취소") { _, _ -> }.show()

        }

        binding.btnMain4Noti.setOnClickListener {
            startActivity(Intent(requireContext(), NotiActivity::class.java))
        }

        return binding.root
    }

    override fun onResume() {
        val user = UserCache.getUser(requireContext())
        viewModel.user.value = user
        viewModel.topicChecked.value = user.topicNotificationStatus
        viewModel.commentChecked.value = user.commentNotificationStatus
        super.onResume()
    }

    private fun checkChange() {
        val authManager = AuthManager()
        authManager.setUserData(
            UserCache.getToken(requireContext()),
            UpdateInfoRequest(
                null,
                null,
                viewModel.topicChecked.value,
                viewModel.commentChecked.value,
                null,
                null
            ),
            onResponse = { commonResponse, userModel ->
                try {
                    UserCache.setUser(requireContext(), userModel)
                } catch (e: IllegalStateException) {
                    e.printStackTrace()
                }
            }, onFailure = {
                it.printStackTrace()
            }
        )
    }
}
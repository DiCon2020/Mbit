package com.david0926.mbit.ui.personality

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.david0926.mbit.R
import com.david0926.mbit.data.auth.UpdateInfoRequest
import com.david0926.mbit.databinding.FragmentPersonalityTest2Binding
import com.david0926.mbit.network.auth.AuthManager
import com.david0926.mbit.ui.dialog.LoadingDialog
import com.david0926.mbit.ui.login.LoginActivity

class PersonalityTest2Fragment : Fragment() {

    lateinit var viewModel: PersonalityTestViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentPersonalityTest2Binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_personality_test_2, container, false
        )
        binding.lifecycleOwner = requireActivity()

        viewModel = ViewModelProvider(requireActivity()).get(PersonalityTestViewModel::class.java)
        binding.viewModel = viewModel

        binding.btnPersonalityTestMore.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.link_personality)))
            startActivity(intent)
        }

        binding.btnPersonalityTestFinish.setOnClickListener {
            val bundle = requireActivity().intent.extras
            val token = bundle!!.getString("token")

            val dialog = LoadingDialog(requireActivity())
            dialog.setMessage("성격 유형 등록중...").show()

            val authManager = AuthManager()

            authManager.setUserData(token!!,
                UpdateInfoRequest(
                    null,
                    viewModel.personality.joinToString("")
                        .replace("1", "").replace("2", ""),
                    null,
                    null,
                    null
                ),
                onResponse = { response, data ->
                    if (response.status != 200) {
                        dialog.cancel()
                        Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT)
                            .show()
                        return@setUserData
                    }
                    dialog.success("성격유형 등록 성공!") {
                        startActivity(Intent(requireContext(), LoginActivity::class.java))
                        requireActivity().finish()
                    }
                },
                onFailure = {
                    dialog.cancel()
                    Toast.makeText(requireContext(), "성격 유형 등록에 실패했습니다.", Toast.LENGTH_SHORT).show()
                    it.printStackTrace()
                })
        }

        return binding.root
    }
}
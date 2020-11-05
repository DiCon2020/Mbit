package com.david0926.mbit.ui.register

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.david0926.mbit.R
import com.david0926.mbit.databinding.ActivityRegisterBinding
import gun0912.tedkeyboardobserver.TedKeyboardObserver
import java.util.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityRegisterBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.lifecycleOwner = this

        val viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.fragments.addAll(
            listOf(
                Register1Fragment(),
                Register2Fragment(),
                Register3Fragment()
            )
        )

        TedKeyboardObserver(this).listen {
            if (it) {
                viewModel.errorMsg.value = ""
                binding.scrollRegister.smoothScrollTo(0, binding.scrollRegister.bottom)
            }
        }
    }
}
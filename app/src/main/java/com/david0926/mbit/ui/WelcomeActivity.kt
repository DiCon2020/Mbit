package com.david0926.mbit.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.david0926.mbit.R
import com.david0926.mbit.databinding.ActivityWelcomeBinding
import com.david0926.mbit.ui.login.LoginActivity
import com.david0926.mbit.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityWelcomeBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_welcome)
        binding.lifecycleOwner = this

        btnWelcomeLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        btnWelcomeRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }
}
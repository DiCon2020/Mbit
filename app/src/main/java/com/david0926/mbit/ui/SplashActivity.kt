package com.david0926.mbit.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.david0926.mbit.R
import com.david0926.mbit.databinding.ActivitySplashBinding
import com.david0926.mbit.ui.login.LoginActivity
import com.david0926.mbit.ui.main.MainActivity
import com.david0926.mbit.ui.onboard.OnBoardActivity
import com.david0926.mbit.util.SharedPreferenceUtil
import com.david0926.mbit.util.UserCache

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySplashBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_splash)

        Handler(mainLooper).postDelayed({

            var state = SharedPreferenceUtil.getString(this, "state", "on_board")

            // TODO: debug - remove this line to show onboard screen once
            //state = "on_board"

            when (state) {
                "on_board" ->
                    startActivity(Intent(this, OnBoardActivity::class.java))
                else -> {
                    if (UserCache.getUser(this) == null)
                        startActivity(Intent(this, LoginActivity::class.java))
                    else startActivity(Intent(this, MainActivity::class.java))
                }
            }

            finish()
        }, 2000)

    }
}
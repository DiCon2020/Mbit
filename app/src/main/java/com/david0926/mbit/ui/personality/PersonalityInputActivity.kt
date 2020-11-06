package com.david0926.mbit.ui.personality

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.david0926.mbit.R
import com.david0926.mbit.data.auth.UpdateInfoRequest
import com.david0926.mbit.databinding.ActivityPersonalityInputBinding
import com.david0926.mbit.network.auth.AuthManager
import com.david0926.mbit.ui.dialog.LoadingDialog
import com.david0926.mbit.ui.login.LoginActivity

class PersonalityInputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityPersonalityInputBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_personality_input)
        binding.lifecycleOwner = this

        val viewModel = ViewModelProvider(this).get(PersonalityInputActivityViewModel::class.java)
        binding.viewModel = viewModel

        binding.btnInputFinish.setOnClickListener {
            val bundle = intent.extras
            val token = bundle!!.getString("token")
//            val user = bundle.getSerializable("user") as UserModel
//            user.personalityType = viewModel.selected.joinToString()

            val dialog = LoadingDialog(this)
            dialog.setMessage("성격 유형 등록중...").show()

            val authManager = AuthManager()

            authManager.setUserData(token!!,
                UpdateInfoRequest(
                    null,
                    viewModel.selected.joinToString(""),
                    null,
                    null,
                    null
                ),
                onResponse = { response, data ->
                    dialog.cancel()
                    if (response.status != 200) {
                        Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                        return@setUserData
                    }
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                },
                onFailure = {
                    dialog.cancel()
                    Toast.makeText(this, "성격 유형 등록에 실패했습니다.", Toast.LENGTH_SHORT).show()
                    it.printStackTrace()
                })
        }
    }
}
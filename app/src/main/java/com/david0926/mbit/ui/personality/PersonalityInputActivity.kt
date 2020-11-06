package com.david0926.mbit.ui.personality

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.david0926.mbit.R
import com.david0926.mbit.databinding.ActivityPersonalityInputBinding

class PersonalityInputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityPersonalityInputBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_personality_input)
        binding.lifecycleOwner = this

        val viewModel = ViewModelProvider(this).get(PersonalityInputActivityViewModel::class.java)
        binding.viewModel = viewModel


    }
}
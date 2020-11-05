package com.david0926.mbit.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.david0926.mbit.R
import com.david0926.mbit.databinding.ActivityMainBinding
import com.david0926.mbit.ui.main.main1.Main1Fragment
import com.david0926.mbit.ui.main.main2.Main2Fragment
import com.david0926.mbit.ui.main.main3.Main3Fragment
import com.david0926.mbit.ui.main.main4.Main4Fragment


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.fragments.addAll(
            listOf(
                Main1Fragment(),
                Main2Fragment(),
                Main3Fragment(),
                Main4Fragment()
            )
        )

        binding.bottomMain.setOnNavigationItemSelectedListener {
            viewModel.page.value = when (it.itemId) {
                R.id.action_1 -> 0
                R.id.action_2 -> 1
                R.id.action_3 -> 2
                else -> 3
            }
            return@setOnNavigationItemSelectedListener true
        }
    }
}

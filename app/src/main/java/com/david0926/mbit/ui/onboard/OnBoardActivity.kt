package com.david0926.mbit.ui.onboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.david0926.mbit.R
import com.david0926.mbit.databinding.ActivityOnBoardBinding
import com.david0926.mbit.ui.WelcomeActivity
import com.david0926.mbit.util.SharedPreferenceUtil

class OnBoardActivity : AppCompatActivity() {

    lateinit var viewModel: OnBoardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityOnBoardBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_on_board)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(OnBoardViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.fragments.addAll(
            listOf(
                OnBoardPagerFragment.newInstance(R.layout.fragment_on_board_1),
                OnBoardPagerFragment.newInstance(R.layout.fragment_on_board_2),
                OnBoardPagerFragment.newInstance(R.layout.fragment_on_board_3)
            )
        )

        val adapter = OnBoardPagerAdapter(this, viewModel.fragments)
        binding.pagerOnBoard.adapter = adapter

        binding.btnOnBoardFinish.setOnClickListener { finishOnBoard() }
        binding.btnOnBoardSkip.setOnClickListener { finishOnBoard() }
    }

    private fun finishOnBoard() {
        SharedPreferenceUtil.put(this@OnBoardActivity, "state", "login")
        startActivity(Intent(this@OnBoardActivity, WelcomeActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        if (!viewModel.isFirstPage) viewModel.previousPage() else super.onBackPressed()
    }
}
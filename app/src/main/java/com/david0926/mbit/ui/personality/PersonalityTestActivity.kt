package com.david0926.mbit.ui.personality

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.david0926.mbit.R
import com.david0926.mbit.databinding.ActivityPersonalityTestBinding
import com.david0926.mbit.ui.register.RegisterActivity

class PersonalityTestActivity : AppCompatActivity() {

    lateinit var viewModel: PersonalityTestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityPersonalityTestBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_personality_test)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(PersonalityTestViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.questions1.addAll(resources.getStringArray(R.array.personality_test_1))
        viewModel.questions2.addAll(resources.getStringArray(R.array.personality_test_2))

        viewModel.fragments.addAll(
            listOf(
                PersonalityTest1Fragment(),
                PersonalityTest2Fragment()
            )
        )

    }

    override fun onBackPressed() {
        if (viewModel.page.value == 0) {
            val userIntent = Intent(this, RegisterActivity::class.java)
            userIntent.putExtras(intent.extras!!)
            startActivity(userIntent)

            super.onBackPressed()
        } else viewModel.previousPage()
    }

    companion object {
        @SuppressLint("UseCompatTextViewDrawableApis")
        @JvmStatic
        @BindingAdapter("bindDrawableTint")
        fun bindDrawableTint(v: Button, color: Int?) {
            if (color != null) v.compoundDrawableTintList = ColorStateList.valueOf(color)
        }
    }

}
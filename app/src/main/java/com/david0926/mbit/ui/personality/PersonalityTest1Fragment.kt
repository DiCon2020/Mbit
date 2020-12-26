package com.david0926.mbit.ui.personality

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableList
import androidx.databinding.ObservableList.OnListChangedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.david0926.mbit.R
import com.david0926.mbit.databinding.FragmentPersonalityTest1Binding

class PersonalityTest1Fragment : Fragment() {

    lateinit var viewModel: PersonalityTestViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentPersonalityTest1Binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_personality_test_1, container, false
        )
        binding.lifecycleOwner = requireActivity()

        viewModel = ViewModelProvider(requireActivity()).get(PersonalityTestViewModel::class.java)
        binding.viewModel = viewModel

        binding.btnPersonalityTest1.setOnClickListener {
            val currentQuestion = viewModel.question.value!!
            viewModel.personality[currentQuestion] =
                checkPersonality(currentQuestion, true)
        }

        binding.btnPersonalityTest2.setOnClickListener {
            val currentQuestion = viewModel.question.value!!
            viewModel.personality[currentQuestion] =
                checkPersonality(currentQuestion, false)
        }

        binding.btnPersonalityTestPrevious.setOnClickListener { viewModel.previousQuestion() }
        binding.btnPersonalityTestNext.setOnClickListener {
            if (viewModel.isLastQuestion.value!!)
                viewModel.nextPage()
            else viewModel.nextQuestion()
        }

        viewModel.question.observe(viewLifecycleOwner, {
            checkIsLastQuestion()
            checkIsNextQuestionEnabled()
        })

        viewModel.personality.addOnListChangedCallback(object :
            OnListChangedCallback<ObservableList<String>>() {

            override fun onItemRangeChanged(
                sender: ObservableList<String>?,
                positionStart: Int,
                itemCount: Int
            ) {
                checkIsNextQuestionEnabled()
            }

            override fun onChanged(sender: ObservableList<String>?) {
                checkIsNextQuestionEnabled()
            }

            override fun onItemRangeInserted(
                sender: ObservableList<String>?,
                positionStart: Int,
                itemCount: Int
            ) {

            }

            override fun onItemRangeMoved(
                sender: ObservableList<String>?,
                fromPosition: Int,
                toPosition: Int,
                itemCount: Int
            ) {

            }

            override fun onItemRangeRemoved(
                sender: ObservableList<String>?,
                positionStart: Int,
                itemCount: Int
            ) {

            }
        })

        return binding.root
    }

    private fun checkIsLastQuestion() {
        viewModel.isLastQuestion.value = viewModel.question.value == viewModel.maxQuestion - 1
    }

    private fun checkIsNextQuestionEnabled() {
        viewModel.isNextQuestionEnabled.value =
            viewModel.personality[viewModel.question.value!!].isNotEmpty()
    }

    private fun checkPersonality(question: Int, checkFirst: Boolean): String {
        when (question) {
            0 -> return if (checkFirst) "E1" else "I2"
            1 -> return if (checkFirst) "S1" else "N2"
            2 -> return if (checkFirst) "T1" else "F2"
            3 -> return if (checkFirst) "J1" else "P2"
        }
        return ""
    }
}
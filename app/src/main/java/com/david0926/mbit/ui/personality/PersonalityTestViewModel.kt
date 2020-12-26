package com.david0926.mbit.ui.personality

import androidx.databinding.ObservableArrayList
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PersonalityTestViewModel : ViewModel() {

    //activity
    val fragments = ObservableArrayList<Fragment>()
    val page = MutableLiveData(0)

    fun nextPage() {
        val currentPage = page.value!!
        if (currentPage < fragments.size - 1) page.value = currentPage + 1
    }

    fun previousPage() {
        val currentPage = page.value!!
        if (currentPage > 0) page.value = currentPage - 1
    }

    //fragment 1
    val personality = ObservableArrayList<String>()

    val maxQuestion = 4
    val question = MutableLiveData(0)

    val questions1 = ObservableArrayList<String>()
    val questions2 = ObservableArrayList<String>()

    val isLastQuestion = MutableLiveData(false)
    val isNextQuestionEnabled = MutableLiveData(false)

    init {
        for (i in 0 until maxQuestion) personality.add("")
    }

    fun nextQuestion() {
        val currentQuestion = question.value!!
        if (currentQuestion < maxQuestion - 1) question.value = currentQuestion + 1
    }

    fun previousQuestion() {
        val currentQuestion = question.value!!
        if (currentQuestion > 0) question.value = currentQuestion - 1
    }
}
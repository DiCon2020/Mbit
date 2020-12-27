package com.david0926.mbit.ui.main.main2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.david0926.mbit.data.topic.Topic
import com.david0926.mbit.data.topic.TopicVoteRequest
import com.david0926.mbit.network.topic.TopicManager

class Main2FragmentViewModel : ViewModel() {
    var personality = ""

    val todayTopic = MutableLiveData<Topic>()
    val lastTopic = MutableLiveData<Topic>()

    val isLoaded = MutableLiveData(false)

    fun getLastTopic(token: String) {
        isLoaded.value = false
        val topicManager = TopicManager()
        topicManager.getLastTopics(token, onResponse = { _, arrayList ->
            isLoaded.value = true
            todayTopic.value = arrayList!![0]
            lastTopic.value = arrayList[1]
        }, onFailure = {
            isLoaded.value = true
            it.printStackTrace()
        })
    }

    fun voteTopic(token: String, status: String) {
        isLoaded.value = false
        val topicManager = TopicManager()
        topicManager.voteTopic(
            token,
            TopicVoteRequest(todayTopic.value!!.date, status),
            onResponse = { _, arrayList ->
                isLoaded.value = true
                todayTopic.value = arrayList!![0]
                lastTopic.value = arrayList[1]
            },
            onFailure = {
                isLoaded.value = true
                it.printStackTrace()
            })
    }
}
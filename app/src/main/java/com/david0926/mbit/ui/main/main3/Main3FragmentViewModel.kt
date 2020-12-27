package com.david0926.mbit.ui.main.main3

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.david0926.mbit.data.personality.PersonalityResponse
import com.david0926.mbit.network.personality.PersonalityManager

class Main3FragmentViewModel : ViewModel() {
    var personality = MutableLiveData<PersonalityResponse>()
    val isLoaded = MutableLiveData(false)

    fun getPersonality(token: String) {
        isLoaded.value = false
        val personalityManager = PersonalityManager()
        personalityManager.getPersonalityType(token, onResponse = { response, data ->
            isLoaded.value = true
            if (response.status != 200) {
                Log.d("baam", "getPersonality: " + response.message)
                return@getPersonalityType
            }
            personality.value = data
        }, onFailure = {
            isLoaded.value = true
            it.printStackTrace()
        })
    }
}
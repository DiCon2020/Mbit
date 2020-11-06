package com.david0926.mbit.ui.main.main3

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.david0926.mbit.data.personality.PersonalityResponse
import com.david0926.mbit.network.personality.PersonalityManager

class Main3FragmentViewModel : ViewModel() {
    var personality = MutableLiveData<PersonalityResponse>()

    fun getPersonality(token: String) {
        val personalityManager = PersonalityManager()

        personalityManager.getPersonalityType(token, onResponse = { response, data ->
            if (response.status != 200) {
                Log.d("baam", "getPersonality: " + response.message)
                return@getPersonalityType
            }
            personality.value = data
        }, onFailure = {
            it.printStackTrace()
        })
    }
}
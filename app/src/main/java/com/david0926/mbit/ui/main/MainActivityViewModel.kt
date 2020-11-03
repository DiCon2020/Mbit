package com.david0926.mbit.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    var test: MutableLiveData<String> = MutableLiveData("")
}
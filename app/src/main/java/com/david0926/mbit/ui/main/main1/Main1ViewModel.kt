package com.david0926.mbit.ui.main.main1

import androidx.databinding.ObservableArrayList
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Main1ViewModel : ViewModel() {
    val fragments = ObservableArrayList<Fragment>()
}
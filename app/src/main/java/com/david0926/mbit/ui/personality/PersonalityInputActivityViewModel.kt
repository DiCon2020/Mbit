package com.david0926.mbit.ui.personality

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel

class PersonalityInputActivityViewModel : ViewModel() {
    val selected = ObservableArrayList<String>()

    init {
        for (i in 0..3) selected.add("")
    }
}
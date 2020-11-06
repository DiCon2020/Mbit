package com.david0926.mbit.ui.main.article

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ArticleUploadActivityViewModel : ViewModel() {

    val text = MutableLiveData("")
    val photo = MutableLiveData<Uri>()

    val private = MutableLiveData(false)

}
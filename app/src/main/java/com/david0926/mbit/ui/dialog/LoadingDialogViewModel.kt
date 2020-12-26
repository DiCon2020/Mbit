package com.david0926.mbit.ui.dialog;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoadingDialogViewModel extends ViewModel {
    public MutableLiveData<String> msg = new MutableLiveData<>("");
}

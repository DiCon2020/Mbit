package com.david0926.mbit.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.david0926.mbit.R;
import com.david0926.mbit.databinding.DialogLoadingBinding;

public class LoadingDialog extends Dialog {

    private FragmentActivity mActivity;
    private String msg = "";

    private DialogLoadingBinding binding;
    private LoadingDialogViewModel viewModel;

    public LoadingDialog(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        super.setCancelable(false);
        mActivity = fragmentActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.dialog_loading, null, false);
        setContentView(binding.getRoot());

        binding.setLifecycleOwner(mActivity);

        ViewModelProvider.NewInstanceFactory f = new ViewModelProvider.NewInstanceFactory();
        viewModel = f.create(LoadingDialogViewModel.class);

        binding.setViewModel(viewModel);

        viewModel.msg.setValue(msg);
    }

    public LoadingDialog setMessage(String msg) {
        if (viewModel != null) viewModel.msg.setValue(msg);
        else this.msg = msg;
        return this;
    }
}
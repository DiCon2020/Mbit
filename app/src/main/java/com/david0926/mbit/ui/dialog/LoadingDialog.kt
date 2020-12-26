package com.david0926.mbit.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.david0926.mbit.R
import com.david0926.mbit.databinding.DialogLoadingBinding

class LoadingDialog(fragmentActivity: FragmentActivity) : Dialog(fragmentActivity) {

    private val mActivity: FragmentActivity
    private var msg = ""

    lateinit var binding: DialogLoadingBinding
    var viewModel: LoadingDialogViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.dialog_loading, null, false)
        setContentView(binding.root)
        binding.lifecycleOwner = mActivity

        viewModel = NewInstanceFactory().create(LoadingDialogViewModel::class.java)
        binding.viewModel = viewModel

        viewModel!!.msg.value = msg
    }

    fun setMessage(msg: String): LoadingDialog {
        if (viewModel == null) this.msg = msg
        else viewModel!!.msg.value = msg
        return this
    }

    fun success(msg: String, onSuccess: () -> Unit) {
        if(viewModel == null) return

        viewModel!!.isSuccess.value = true
        setMessage(msg)
        Handler(Looper.getMainLooper()).postDelayed({
            onSuccess.invoke()
            cancel()
        }, 1500)
    }

    init {
        super.setCancelable(false)
        mActivity = fragmentActivity
    }
}
package com.david0926.mbit.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.david0926.mbit.R
import com.david0926.mbit.databinding.FragmentRegister1Binding
import java.util.regex.Pattern

class Register1Fragment : Fragment() {

    lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentRegister1Binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_register_1, container, false
        )
        binding.lifecycleOwner = requireActivity()

        viewModel = ViewModelProvider(requireActivity()).get(RegisterViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.onNextClick = { viewModel.nextPage() }

        viewModel.email.observe(viewLifecycleOwner, { checkNextEnabled() })
        viewModel.pw.observe(viewLifecycleOwner, { checkNextEnabled() })
        viewModel.pwCheck.observe(viewLifecycleOwner, { checkNextEnabled() })

        binding.btnRegister1Next.setOnClickListener { viewModel.onNextClick.invoke() }

        return binding.root
    }

    private fun checkNextEnabled() {
        viewModel.nextEnabled.value =
            android.util.Patterns.EMAIL_ADDRESS.matcher(viewModel.email.value!!).matches()
                    && isValidPw(viewModel.pw.value!!) && viewModel.pw.value.equals(viewModel.pwCheck.value)
    }

    private fun isValidPw(target: String): Boolean {
        //6~24 letters, 0~9 + A-z
        val p = Pattern.compile("(^.*(?=.{6,24})(?=.*[0-9])(?=.*[A-z]).*$)")
        val m = p.matcher(target)
        //except korean letters
        return m.find() && !target.matches(Regex(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*"))
    }
}
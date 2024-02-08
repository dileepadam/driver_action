package com.damc.asignment_zero_one_tech.ui.register

import android.os.Bundle
import android.provider.Settings.Global
import android.view.View
import androidx.core.widget.doOnTextChanged
import com.damc.asignment_zero_one_tech.R
import com.damc.asignment_zero_one_tech.databinding.FragmentRegisterBinding
import com.damc.asignment_zero_one_tech.ui.BaseFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>() {

    override val layoutId: Int = R.layout.fragment_register
    override val viewModel: RegisterViewModel
        get() {
            val viewModel by activityViewModel<RegisterViewModel>()
            return viewModel
        }

    override fun onReady(savedInstanceState: Bundle?) {
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    fun onClickLogin(view: View) {
        viewModel.registerToLogin()
    }

    fun onClickRegister(view: View) {
        GlobalScope.launch {
            viewModel.validateFields(
                binding.etUsername.text.toString(),
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString(),
                binding.etPasswordConfirm.text.toString(), requireContext()
            )
        }

    }



}
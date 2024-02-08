package com.damc.asignment_zero_one_tech.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController

abstract class BaseFragment<BINDING : ViewDataBinding, VM : BaseViewModel>() : Fragment() {
    @get:LayoutRes
    protected abstract val layoutId: Int
    protected abstract val viewModel: VM
    protected lateinit var binding: BINDING

    var navController: NavController? = null

    protected val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // Do nothing to prevent back navigation
        }
    }

    protected abstract fun onReady(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            layoutInflater, layoutId, container, false
        )

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
//            setVariable(BR, viewModel)
//            setVariable(BR.fragment, this@BaseFragment)
        }

        return binding.root
    }
}
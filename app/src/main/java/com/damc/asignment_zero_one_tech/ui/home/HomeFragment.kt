package com.damc.asignment_zero_one_tech.ui.home

import android.os.Bundle
import com.damc.asignment_zero_one_tech.R
import com.damc.asignment_zero_one_tech.databinding.FragmentHomeBinding
import com.damc.asignment_zero_one_tech.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val layoutId: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel
        get() {
            val viewModel by activityViewModel<HomeViewModel>()
            return viewModel
        }

    override fun onReady(savedInstanceState: Bundle?) {

    }


}
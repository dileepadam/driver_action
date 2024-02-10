package com.damc.asignment_zero_one_tech.ui.home.ongoing

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.damc.asignment_zero_one_tech.R
import com.damc.asignment_zero_one_tech.databinding.FragmentOnGoingBinding
import com.damc.asignment_zero_one_tech.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class OnGoingFragment : BaseFragment<FragmentOnGoingBinding, OnGoingViewModel>() {

    companion object {
        fun newInstance() = OnGoingFragment()
    }
    override val layoutId: Int = R.layout.fragment_on_going
    override val viewModel: OnGoingViewModel
        get() {
            val viewModel by activityViewModel<OnGoingViewModel>()
            return viewModel
        }

    override fun onReady(savedInstanceState: Bundle?) {

    }


}
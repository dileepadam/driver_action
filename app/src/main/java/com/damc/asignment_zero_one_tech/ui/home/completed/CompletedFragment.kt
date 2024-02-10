package com.damc.asignment_zero_one_tech.ui.home.completed

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.damc.asignment_zero_one_tech.R
import com.damc.asignment_zero_one_tech.databinding.FragmentCompletedBinding
import com.damc.asignment_zero_one_tech.ui.BaseFragment
import com.damc.asignment_zero_one_tech.ui.home.homeMian.HomeMianViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class CompletedFragment : BaseFragment<FragmentCompletedBinding, CompletedViewModel>() {

    companion object {
        fun newInstance() = CompletedFragment()
    }

    override val layoutId: Int = R.layout.fragment_completed
    override val viewModel: CompletedViewModel
        get() {
            val viewModel by activityViewModel<CompletedViewModel>()
            return viewModel
        }

    override fun onReady(savedInstanceState: Bundle?) {

    }

}
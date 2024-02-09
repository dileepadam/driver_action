package com.damc.asignment_zero_one_tech.ui.home.homeMian

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.damc.asignment_zero_one_tech.R
import com.damc.asignment_zero_one_tech.databinding.FragmentHomeMianBinding
import com.damc.asignment_zero_one_tech.ui.BaseFragment
import com.damc.asignment_zero_one_tech.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class HomeMianFragment : BaseFragment<FragmentHomeMianBinding, HomeMianViewModel>() {

    companion object {
        fun newInstance() = HomeMianFragment()
    }

    override val layoutId: Int = R.layout.fragment_home_mian
    override val viewModel: HomeMianViewModel
        get() {
            val viewModel by activityViewModel<HomeMianViewModel>()
            return viewModel
        }

    override fun onReady(savedInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }



}
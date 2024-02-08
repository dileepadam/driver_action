package com.damc.asignment_zero_one_tech.ui.launcher

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.damc.asignment_zero_one_tech.R
import com.damc.asignment_zero_one_tech.databinding.FragmentLauncherBinding
import com.damc.asignment_zero_one_tech.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class LauncherFragment : BaseFragment<FragmentLauncherBinding, LauncherViewModel>() {

    override val layoutId: Int = R.layout.fragment_launcher

    override val viewModel: LauncherViewModel
        get() {
            val viewModel by activityViewModel<LauncherViewModel>()
            return viewModel
        }

    override fun onReady(savedInstanceState: Bundle?) {

    }


}
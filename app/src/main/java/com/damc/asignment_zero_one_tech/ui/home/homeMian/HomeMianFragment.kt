package com.damc.asignment_zero_one_tech.ui.home.homeMian

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.damc.asignment_zero_one_tech.R
import com.damc.asignment_zero_one_tech.app.AssignmentApplication
import com.damc.asignment_zero_one_tech.databinding.FragmentHomeMianBinding
import com.damc.asignment_zero_one_tech.ui.BaseFragment
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
        viewModel.getFavouriteBookCount((requireActivity().application as AssignmentApplication).getLoginUser().userId)
        setView()
    }

    fun setView() {
        viewModel.favouriteCount.observe(this, Observer {
            if (viewModel.favouriteCount.value == 0) {
                binding.rvMyFavourite.visibility = View.GONE
                binding.tvStatus.visibility = View.VISIBLE
            } else {
                binding.rvMyFavourite.visibility = View.VISIBLE
                binding.tvStatus.visibility = View.GONE
            }
        })
    }

}
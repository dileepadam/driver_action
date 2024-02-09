package com.damc.asignment_zero_one_tech.ui.home.allBooks

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.damc.asignment_zero_one_tech.R
import com.damc.asignment_zero_one_tech.databinding.FragmentAllBooksBinding
import com.damc.asignment_zero_one_tech.ui.BaseFragment
import com.damc.asignment_zero_one_tech.ui.home.homeMian.HomeMianViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class AllBooksFragment : BaseFragment<FragmentAllBooksBinding, AllBooksViewModel>() {

    companion object {
        fun newInstance() = AllBooksFragment()
    }

    override val layoutId: Int = R.layout.fragment_all_books
    override val viewModel: AllBooksViewModel
        get() {
            val viewModel by activityViewModel<AllBooksViewModel>()
            return viewModel
        }

    override fun onReady(savedInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }

}
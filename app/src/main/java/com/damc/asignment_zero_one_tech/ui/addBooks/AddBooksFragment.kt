package com.damc.asignment_zero_one_tech.ui.addBooks

import android.os.Bundle
import com.damc.asignment_zero_one_tech.R
import com.damc.asignment_zero_one_tech.databinding.FragmentAllBooksBinding
import com.damc.asignment_zero_one_tech.ui.BaseFragment
import com.damc.asignment_zero_one_tech.ui.home.allBooks.AllBooksViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class AddBooksFragment : BaseFragment<FragmentAllBooksBinding, AllBooksViewModel>() {

    override val layoutId: Int = R.layout.fragment_add_books
    override val viewModel: AllBooksViewModel
        get() {
            val viewModel by activityViewModel<AllBooksViewModel>()
            return viewModel
        }

    override fun onReady(savedInstanceState: Bundle?) {
        
    }


}
package com.damc.asignment_zero_one_tech.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.damc.asignment_zero_one_tech.R
import com.damc.asignment_zero_one_tech.databinding.FragmentHomeBinding
import com.damc.asignment_zero_one_tech.ui.BaseFragment
import com.damc.asignment_zero_one_tech.ui.home.allBooks.AllBooksFragment
import com.damc.asignment_zero_one_tech.ui.home.completed.CompletedFragment
import com.damc.asignment_zero_one_tech.ui.home.homeMian.HomeMianFragment
import com.damc.asignment_zero_one_tech.ui.home.ongoing.OnGoingFragment
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val layoutId: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel
        get() {
            val viewModel by activityViewModel<HomeViewModel>()
            return viewModel
        }

    override fun onReady(savedInstanceState: Bundle?) {
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
        changeFragment()
    }


    private fun setCurrFragment(fragment: Fragment): Boolean {
        childFragmentManager.beginTransaction().apply {
            replace(R.id.id_fragment_container, fragment)
            commit()
        }
        return true
    }

    fun changeFragment() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.it_home -> setCurrFragment(HomeMianFragment.newInstance())
                R.id.it_all -> setCurrFragment(AllBooksFragment.newInstance())
                R.id.it_ongoing -> setCurrFragment(OnGoingFragment.newInstance())
                R.id.it_competed -> setCurrFragment(CompletedFragment.newInstance())
                else -> setCurrFragment(HomeMianFragment.newInstance())
            }
        }
        binding.bottomNavigation.selectedItemId = R.id.it_home
    }
}
package com.fawry.tmdb.ui.home

import android.os.Bundle
import android.view.View
import com.fawry.tmdb.common.BaseFragment
import com.fawry.tmdb.databinding.HomeFragmentBinding

class HomeFragment : BaseFragment<HomeViewModel, HomeFragmentBinding>(
	HomeFragmentBinding::inflate, HomeViewModel::class.java
) {

    companion object {
        fun newInstance() = HomeFragment()
    }

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

	}

}

package com.ramo.newsapp.ui.home

import com.ramo.newsapp.R
import com.ramo.newsapp.core.BaseFragment
import com.ramo.newsapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override fun init() {

    }
}
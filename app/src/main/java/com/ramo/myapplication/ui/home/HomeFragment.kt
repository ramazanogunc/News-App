package com.ramo.myapplication.ui.home

import com.ramo.myapplication.R
import com.ramo.myapplication.core.BaseFragment
import com.ramo.myapplication.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override fun init() {

    }
}
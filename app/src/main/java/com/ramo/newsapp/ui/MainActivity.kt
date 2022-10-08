package com.ramo.newsapp.ui

import com.ramo.newsapp.R
import com.ramo.newsapp.core.BaseActivity
import com.ramo.newsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override fun init() = Unit
}
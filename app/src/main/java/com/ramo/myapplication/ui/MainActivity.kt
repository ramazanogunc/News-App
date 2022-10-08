package com.ramo.myapplication.ui

import com.ramo.myapplication.R
import com.ramo.myapplication.core.BaseActivity
import com.ramo.myapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override fun init() = Unit
}
package com.ramo.newsapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.ramo.newsapp.util.FrEventHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FrEventHelper.setContext(this)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
package com.ramo.newsapp.util

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings

object Utils {

    @SuppressLint("HardwareIds")
    fun getDeviceId(context: Context): String =
        Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

}
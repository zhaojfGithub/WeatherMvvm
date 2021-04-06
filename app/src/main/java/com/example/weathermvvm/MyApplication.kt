package com.example.weathermvvm

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    companion object {
        lateinit var context: Context
        const val TOKEN = "w7QNti6DIsBxoaze"
        var isDebugMode: Boolean = false
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}
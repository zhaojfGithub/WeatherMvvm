package com.example.weathermvvm

import android.app.Application
import android.content.Context

class MyApplication : Application() {

    companion object {
        lateinit var context: Context
        const val TOKEN = "w7QNti6DIsBxoaze"
        var isDebugMode: Boolean = true
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}
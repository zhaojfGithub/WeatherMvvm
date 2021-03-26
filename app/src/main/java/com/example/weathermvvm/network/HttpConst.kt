package com.example.weathermvvm.network

object HttpConst {

    private const val API_Cloud = "https://api.caiyunapp.com"

    private const val API_LOCALHOST = "http://10.0.2.2:8080"


    val apiColud by lazy { ApiFactory.createService(API_Cloud, ApiService::class.java) }

    val apiLocahost by lazy { ApiFactory.createService(API_LOCALHOST, ApiService::class.java) }
}
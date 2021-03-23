package com.example.weathermvvm.network

object HttpConst {

    var IS_API = true

    private const val API_Cloud = "https://api.caiyunapp.com"

    private const val API_LOCALHOST = "http://localhost:8080"


    val api by lazy { ApiFactory.createService(if (IS_API) API_Cloud else API_LOCALHOST, ApiService::class.java) }

}
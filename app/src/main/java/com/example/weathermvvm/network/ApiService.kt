package com.example.weathermvvm.network

import com.example.weathermvvm.MyApplication
import com.example.weathermvvm.bean.SiteBean
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("v2/place?token=${MyApplication.TOKEN}&lang=zh_CN")
    suspend fun getSite(@Query("query") query: String): ApiCloudResult<SiteBean>
}
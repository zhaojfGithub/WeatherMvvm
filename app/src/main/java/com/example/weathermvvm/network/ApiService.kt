package com.example.weathermvvm.network

import com.example.weathermvvm.MyApplication
import com.example.weathermvvm.bean.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("v2/place?token=${MyApplication.TOKEN}&lang=zh_CN")
    suspend fun getSite(@Query("query") query: String): SiteBean

    @GET("weatherSite/getAllSite")
    suspend fun getAllSite(@Query("user_id") userId: Long): ApiResult<ArrayList<AllSiteBean>>

    @GET("v2.5/${MyApplication.TOKEN}/{lng},{lat}/realtime.json")
    suspend fun getRealtimeWeather(@Path("lng") lng: String, @Path("lat") lat: String): RealTimeBean

    @GET("v2.5/${MyApplication.TOKEN}/{lng},{lat}/daily.json")
    suspend fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String): DailyBean
}
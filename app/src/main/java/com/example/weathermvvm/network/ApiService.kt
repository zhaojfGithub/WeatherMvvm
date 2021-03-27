package com.example.weathermvvm.network

import com.example.weathermvvm.MyApplication
import com.example.weathermvvm.bean.*
import retrofit2.http.*


interface ApiService {

    @GET("v2/place?token=${MyApplication.TOKEN}&lang=zh_CN")
    suspend fun getSite(@Query("query") query: String): SiteBean

    @GET("weatherSite/getAllSite")
    suspend fun getAllSite(@Query("user_id") userId: Long): ApiResult<List<AllSiteBean>>

    @GET("v2.5/${MyApplication.TOKEN}/{lng},{lat}/realtime.json")
    suspend fun getRealtimeWeather(@Path("lng") lng: String, @Path("lat") lat: String): RealTimeBean

    @GET("v2.5/${MyApplication.TOKEN}/{lng},{lat}/daily.json")
    suspend fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String): DailyBean

    @POST("weatherSite/addSite")
    suspend fun setSiteWeather(@Query("user_id") userId: Long,
                               @Query("site") site: String,
                               @Query("lat") lat: String,
                               @Query("lng") lng: String): SignBean

    @DELETE("weatherSite/removeSite")
    suspend fun deleteSiteWeather(@Query("user_id") userId: Long,
                                  @Query("id") id: Long):SignBean
}
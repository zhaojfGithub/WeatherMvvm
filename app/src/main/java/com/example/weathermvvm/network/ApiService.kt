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
                                  @Query("id") id: Long): SignBean

    @GET("weatherFacility/getAllFacility")
    suspend fun getAllFacility(@Query("id") userId: Long): ApiResult<List<FacilityBean>>

    @POST("weatherUserFacility/addUserFacility")
    suspend fun setUserFacility(@Query("user_id") userId: Long,
                                @Query("facility_id") facility_id: Long): SignBean

    @POST("weatherUserFacility/amendUserFacility")
    suspend fun amendUserFacility(@Query("user_id") userId: Long,
                                  @Query("facility_id") facility_id: Long,
                                  @Query("collect") collect: Int): SignBean

    /**
     * 注册
     */
    @POST("weatherUser/register")
    suspend fun registered(@Query("accountNumber") account: Long,
                           @Query("password") password: String): SignBean

    /**
     * 登录
     */
    @POST("weatherUser/login")
    suspend fun login(@Query("accountNumber") account: Long,
                      @Query("password") password: String): ApiResult<List<UserBean>>

    /**
     * 根据id查询个人信息
     */
    @GET("weatherUser/user")
    suspend fun user(@Query("userId") userId: Long): ApiResult<UserBean>

    /**
     * 修改个人信息
     */
    @POST("weatherUser/updateUser")
    suspend fun updateUser(@Body bean: UserBean): SignBean

    /**
     * 反馈
     */
    @POST("weatherFeedback/addFeedback")
    suspend fun addFeedback(@Query("userId") userId:Long,@Query("feedback") feedback:String):SignBean

    /**
     * 查询已经收藏得设备
     */
    @GET("weatherFacility/getUserFacility")
    suspend fun getUserFacility(@Query("id") userId:Long):ApiResult<List<FacilityBean>>
}
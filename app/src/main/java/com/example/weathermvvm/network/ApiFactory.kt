package com.example.weathermvvm.network

import com.example.weathermvvm.MyApplication
import com.example.weathermvvm.MyApplication.Companion.context
import com.example.weathermvvm.common.LogUtils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

object ApiFactory {

    const val TAG = "OKHTTP"

    // 日志拦截器
    private val mLoggingInterceptor: Interceptor by lazy { LoggingInterceptor() }

    // OkHttpClient客户端
    private val mClient: OkHttpClient by lazy { newClient() }


    /**
     * 创建API Service接口实例
     */
    fun <T> createService(baseUrl: String, clazz: Class<T>): T =
            Retrofit.Builder().baseUrl(baseUrl).client(mClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(clazz)

    /**
     * OkHttpClient客户端
     */
    private fun newClient(): OkHttpClient = OkHttpClient.Builder().apply {
        connectTimeout(30, TimeUnit.SECONDS)// 连接时间：30s超时
        readTimeout(10, TimeUnit.SECONDS)// 读取时间：10s超时
        writeTimeout(10, TimeUnit.SECONDS)// 写入时间：10s超时
        retryOnConnectionFailure(true)
        if (MyApplication.isDebugMode) addInterceptor(mLoggingInterceptor)// 仅debug模式启用日志过滤器
    }.build()

    /**
     * 日志拦截器
     */
    private class LoggingInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var response: Response? = null
            try {
                val request = chain.request()
                LogUtils.v(TAG, "request:$request")
                LogUtils.v(TAG, "headers: " + request.headers().toString())
                val t1 = System.nanoTime()
                response = chain.proceed(request)
                val t2 = System.nanoTime()
                LogUtils.v(
                        TAG, String.format(
                        Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                        response.request().url(), (t2 - t1) / 1e6, response.headers()
                )
                )
                val body = response.body()
                val mediaType = body!!.contentType()
                LogUtils.i(TAG, "contentLength:" + body.contentLength())
                val content = body.string()
                LogUtils.i(TAG, "response body:$content")
                return response.newBuilder()
                        .body(ResponseBody.create(mediaType, content))
                        .build()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return response!!
        }
    }
}
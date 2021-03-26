package com.example.weathermvvm.network

/**
 *
 */
data class ApiResult<T>(val code: Int, val msg: String, private val data: T) {
    fun apiData(): T {
        if (code == 200) {
            return data
        } else {
            throw HttpException(500, msg)
        }
    }
}

package com.example.weathermvvm.network

data class ApiCloudResult<T>(val status: String, private val data: T) {
    fun apiData(): T {
        if (status == "ok"){
            return data
        }else{
            throw HttpException(500,status)
        }
    }
}
package com.example.weathermvvm.network

data class ApiCloudResult<T>(val status: String,val query:String, private val place: List<T>) {
    fun apiData(): List<T> {
        if (status == "ok"){
            return place
        }else{
            throw HttpException(500,status)
        }
    }
}
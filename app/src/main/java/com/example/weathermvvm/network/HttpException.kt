package com.example.weathermvvm.network

class HttpException(var code: Int, override var message: String) : RuntimeException()
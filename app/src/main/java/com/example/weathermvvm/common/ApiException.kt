package com.example.weathermvvm.common


class ApiException(val code: Int,val msg: String): Throwable(msg)
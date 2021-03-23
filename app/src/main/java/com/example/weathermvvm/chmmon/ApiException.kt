package com.example.weathermvvm.chmmon


class ApiException(val code: Int,val msg: String): Throwable(msg)
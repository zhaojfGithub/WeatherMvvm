package com.example.weathermvvm.bean

sealed class LoadState(val msg: String) {
    /**
     * 加载中
     */
    class Loading(msg: String = "加载中"): LoadState(msg)

    /**
     * 成功
     */
    class Success(msg: String = "成功"): LoadState(msg)

    /**
     * 失败
     */
    class Fail(msg: String = "失败"): LoadState(msg)
}
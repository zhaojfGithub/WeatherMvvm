package com.example.weathermvvm.chmmon

import android.util.Log
import com.example.weathermvvm.MyApplication

object LogUtils {

    const val TAG = "HTTP"

    fun v(msg: String) {
        if (MyApplication.isDebugMode) {
            Log.v(TAG, msg)
        }
    }

    fun v(tag: String, msg: String) {
        if (MyApplication.isDebugMode) {
            Log.v(tag, msg)
        }
    }

    /**
     * Info:例如一些运行时的状态信息，这些状态信息在出现问题的时候能提供帮助。
     */

    fun i(tag: String, msg: String) {
        if (MyApplication.isDebugMode) {
            Log.i(tag, msg)
        }
    }

    /**
     * Error: 错误信息
     */
    fun e(msg: String) {
        Log.e(TAG, msg)
    }
}
package com.example.weathermvvm.common

import android.widget.Toast
import com.example.weathermvvm.MyApplication

object ToastUtil {
    fun showShort(msg:String){
        Toast.makeText(MyApplication.context,msg,Toast.LENGTH_SHORT).show()
    }
}
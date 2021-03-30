package com.example.weathermvvm.store

import com.example.weathermvvm.MyApplication
import com.example.weathermvvm.bean.AllSiteBean
import com.example.weathermvvm.util.clearSpValue
import com.example.weathermvvm.util.getSpValue
import com.example.weathermvvm.util.putSpValue
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object LoginStore {
    private const val userId = "userId"
    private const val siteList = "siteList"
    private val gson by lazy { Gson() }

    fun isLogin(): Boolean {
        val userId = getSpValue(userId, "")
        return userId.isNotEmpty()
    }

    fun getUserId(): String {
        return getSpValue(this.userId, "")
    }


    fun getSiteList(): ArrayList<AllSiteBean>? {
        val list = getSpValue(siteList, "")
        if (list.isEmpty()||list=="[]"){
            return null
        }
        return gson.fromJson(list,object : TypeToken<List<AllSiteBean>>() {}.type)
    }

    fun setSiteList(list: ArrayList<AllSiteBean>){
        putSpValue(siteList,gson.toJson(list))
    }

    fun setUserId(userId: String){
        putSpValue(this.userId,userId)
    }

    fun clearUserInfo() {
        clearSpValue(userId, MyApplication.context)
        clearSpValue(siteList, MyApplication.context)
    }
}
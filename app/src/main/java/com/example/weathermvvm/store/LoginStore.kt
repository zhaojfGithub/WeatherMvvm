package com.example.weathermvvm.store

import com.example.weathermvvm.bean.AllSiteBean
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

    fun getUserId(): Long {
        val userId = getSpValue(userId, 0L)
        if (userId != 0L) {
            return userId
        }
        return 0L
    }


    fun getSiteList(): ArrayList<AllSiteBean> {
        val list = getSpValue(siteList, "")
        return gson.fromJson(list,object : TypeToken<List<AllSiteBean>>() {}.type)
    }

    fun setSiteList(list: ArrayList<AllSiteBean>){
        putSpValue(siteList,gson.toJson(list))
    }

    fun setUserId(userId: Long){
        putSpValue(this.userId,userId)
    }
}
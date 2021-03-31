package com.example.weathermvvm.ui.main.home

import androidx.lifecycle.MutableLiveData
import com.example.weathermvvm.base.BaseViewModel
import com.example.weathermvvm.bean.AllSiteBean
import com.example.weathermvvm.common.LogUtils
import com.example.weathermvvm.common.launch
import com.example.weathermvvm.network.HttpConst
import com.example.weathermvvm.store.LoginStore

class HomeViewModel : BaseViewModel() {

    val allSiteBean = MutableLiveData<List<AllSiteBean>>()
    val isSite = MutableLiveData<Boolean>()
    val loginData = MutableLiveData<Boolean>()
    private var oneLogin: String = ""

    fun getSiteList() = launch({
        loadState.value = true
        if (!LoginStore.isLogin()) {
            LogUtils.v("进入本地")
            isSite.value = LoginStore.getSiteList().size==0
            allSiteBean.value =LoginStore.getSiteList()
        } else {
            val userId = LoginStore.getUserId().toLong()
            LogUtils.v("进入登录",userId.toString())
            allSiteBean.value = HttpConst.apiLocahost.getAllSite(userId).apiData()
        }
        loadState.value = false
    }, {
        loadState.value = false
    })

    fun loginData() {
        if (LoginStore.isLogin() && oneLogin.isEmpty()) {
            loginData.value = true
        } else loginData.value = !LoginStore.isLogin() && oneLogin.isNotEmpty()
    }
}
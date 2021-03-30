package com.example.weathermvvm.ui.main.home

import androidx.lifecycle.MutableLiveData
import com.example.weathermvvm.base.BaseViewModel
import com.example.weathermvvm.bean.AllSiteBean
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
            if (LoginStore.getSiteList()==null){
                isSite.value = true
            }else{
                isSite.value = false
                allSiteBean.value =LoginStore.getSiteList()
            }
        } else {
            val userId = LoginStore.getUserId().toLong()
            allSiteBean.value = HttpConst.apiLocahost.getAllSite(userId).apiData()
        }
        loadState.value = false
    }, {
        loadState.value = false
    })

    fun loginData() {
        if (LoginStore.isLogin() && oneLogin.isEmpty()) {
            loginData.value = true
        }
    }
}
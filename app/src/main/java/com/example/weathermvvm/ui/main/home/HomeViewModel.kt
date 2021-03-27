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
            allSiteBean.value = HttpConst.apiLocahost.getAllSite(LoginStore.getUserId()).apiData()
        }
        loadState.value = false
    }, {
        loadState.value = false
    })
}
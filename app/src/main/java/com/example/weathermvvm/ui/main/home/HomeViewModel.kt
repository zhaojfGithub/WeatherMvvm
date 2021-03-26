package com.example.weathermvvm.ui.main.home

import androidx.lifecycle.MutableLiveData
import com.example.weathermvvm.base.BaseViewModel
import com.example.weathermvvm.bean.AllSiteBean
import com.example.weathermvvm.common.launch
import com.example.weathermvvm.network.HttpConst
import com.example.weathermvvm.store.LoginStore

class HomeViewModel : BaseViewModel() {

    val allSiteBean = MutableLiveData<ArrayList<AllSiteBean>>()

    fun getSiteList()=launch({
        loadState.value = true
//        if (LoginStore.isLogin()){
//            allSiteBean.value = LoginStore.getSiteList()
//        }else
            allSiteBean.value = HttpConst.apiLocahost.getAllSite(1371743970845048834).apiData()
//        }
        loadState.value = false
    },{
        loadState.value = false
    })
}
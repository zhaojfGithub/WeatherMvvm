package com.example.weathermvvm.ui.main

import androidx.lifecycle.MutableLiveData
import com.example.weathermvvm.base.BaseViewModel
import com.example.weathermvvm.bean.LoadState
import com.example.weathermvvm.bean.SiteBean
import com.example.weathermvvm.chmmon.launch
import com.example.weathermvvm.network.HttpConst

class MainViewModel : BaseViewModel() {

    val data = MutableLiveData<SiteBean>()

    fun getSiteList(site:String) = launch({
        loadState.value = LoadState.Loading()
        HttpConst.IS_API = true
        data.value = HttpConst.api.getSite(site).apiData()
        loadState.value = LoadState.Success()
    },{
        loadState.value = LoadState.Fail()
    })
}
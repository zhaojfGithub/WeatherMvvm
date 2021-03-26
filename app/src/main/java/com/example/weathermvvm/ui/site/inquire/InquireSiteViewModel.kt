package com.example.weathermvvm.ui.site.inquire

import androidx.lifecycle.MutableLiveData
import com.example.weathermvvm.base.BaseViewModel
import com.example.weathermvvm.bean.SiteBean
import com.example.weathermvvm.common.launch
import com.example.weathermvvm.network.HttpConst

class InquireSiteViewModel : BaseViewModel() {
    val siteBean = MutableLiveData<List<SiteBean.Place>>()

    fun getSiteList(site: String) = launch({
        loadState.value = true
        siteBean.value=HttpConst.apiColud.getSite(site).places
        loadState.value = false
    }, {
        loadState.value = false
    })
}
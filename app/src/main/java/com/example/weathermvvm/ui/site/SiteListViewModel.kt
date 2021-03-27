package com.example.weathermvvm.ui.site

import androidx.lifecycle.MutableLiveData
import com.example.weathermvvm.base.BaseViewModel
import com.example.weathermvvm.bean.AllSiteBean
import com.example.weathermvvm.bean.SignBean
import com.example.weathermvvm.common.launch
import com.example.weathermvvm.network.HttpConst
import com.example.weathermvvm.store.LoginStore

class SiteListViewModel : BaseViewModel() {

    val getList = MutableLiveData<List<AllSiteBean>>()
    val setList = MutableLiveData<SignBean>()

    /**
     * lateinit  延迟初始化
     */
    private var arrayList = ArrayList<AllSiteBean>()

    fun getSiteList() = launch({
        loadState.value = true
        if (LoginStore.isLogin()) {
            val userId = LoginStore.getUserId()
            getList.value = HttpConst.apiLocahost.getAllSite(userId).apiData()
        } else {
            if (LoginStore.getSiteList() != null) {
                getList.value = LoginStore.getSiteList()
            }
        }
        loadState.value = false
    }, {
        loadState.value = false
    })

    fun setSiteList(site: String, lat: String, lng: String) = launch({
        loadState.value = true
        if (LoginStore.isLogin()) {
            val userId = LoginStore.getUserId()
            setList.value = HttpConst.apiLocahost.setSiteWeather(userId, site, lat, lng)
        } else {
            arrayList.clear()
            if (LoginStore.getSiteList() != null) {
                arrayList = LoginStore.getSiteList()!!
            }
            val bean = AllSiteBean(null, null, null, null, lat, lng, site, null)
            arrayList.add(bean)
            LoginStore.setSiteList(arrayList)
            val signBean = SignBean(200, "添加成功")
            setList.value = signBean
        }
        loadState.value = false
    }, {
        loadState.value = false
    })

    fun removeSiteList(id: Long?, positon: Int) = launch({
        loadState.value = true
        if (id == null) {
            arrayList.clear()
            arrayList = LoginStore.getSiteList()!!
            arrayList.removeAt(positon)
            LoginStore.setSiteList(arrayList)
            val signBean = SignBean(200, "删除成功")
            setList.value = signBean
        } else {
            val userId = LoginStore.getUserId()
            setList.value = HttpConst.apiLocahost.deleteSiteWeather(userId, id)
        }
        loadState.value = false
    }, {
        loadState.value = false
    })

}

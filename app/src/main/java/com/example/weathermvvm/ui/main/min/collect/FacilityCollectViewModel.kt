package com.example.weathermvvm.ui.main.min.collect

import androidx.lifecycle.MutableLiveData
import com.example.weathermvvm.base.BaseViewModel
import com.example.weathermvvm.bean.FacilityBean
import com.example.weathermvvm.bean.SignBean
import com.example.weathermvvm.common.launch
import com.example.weathermvvm.network.HttpConst
import com.example.weathermvvm.store.LoginStore

class FacilityCollectViewModel : BaseViewModel() {

    val list = MutableLiveData<List<FacilityBean>>()
    val updateFacility = MutableLiveData<SignBean>()

    fun getUserFacility() = launch({
        loadState.value = true
        list.value = HttpConst.apiLocahost.getUserFacility(LoginStore.getUserId().toLong()).apiData()
        loadState.value = false
    },{
        loadState.value = false
    })

    fun updateUserFacility(position: Int) = launch({
        loadState.value = true
        val userId = LoginStore.getUserId().toLong()
        val facilityId = list.value!![position].facilityId
        updateFacility.value = HttpConst.apiLocahost.amendUserFacility(
                userId, facilityId, 1)
        loadState.value = false
    },{
        loadState.value = false

    })
}
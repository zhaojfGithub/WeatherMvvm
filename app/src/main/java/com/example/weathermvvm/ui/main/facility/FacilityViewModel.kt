package com.example.weathermvvm.ui.main.facility

import androidx.lifecycle.MutableLiveData
import com.example.weathermvvm.base.BaseViewModel
import com.example.weathermvvm.bean.FacilityBean
import com.example.weathermvvm.bean.SignBean
import com.example.weathermvvm.common.LogUtils
import com.example.weathermvvm.common.launch
import com.example.weathermvvm.network.HttpConst
import com.example.weathermvvm.store.LoginStore

class FacilityViewModel : BaseViewModel() {

    val list = MutableLiveData<List<FacilityBean>>()
    val setFacility = MutableLiveData<SignBean>()
    val isLogin = MutableLiveData<Boolean>()
    var position: Int = 0
    val loginData = MutableLiveData<Boolean>()
    private var oneLogin: String = ""

    fun getAllFacility() = launch({
        loadState.value = true
        oneLogin = LoginStore.getUserId()
        if (LoginStore.isLogin()) {
            list.value = HttpConst.apiLocahost.getAllFacility(oneLogin.toLong()).apiData()
        } else {
            list.value = HttpConst.apiLocahost.getAllFacility(-1L).apiData()
        }
        loadState.value = false
    }, {
        loadState.value = false
    })

    fun setUserFacility(position: Int) = launch({
        loadState.value = true
        if (LoginStore.isLogin()) {
            this@FacilityViewModel.position = position
            val userId = LoginStore.getUserId().toLong()
            val facilityId = list.value!![position].facilityId
            val collect = list.value!![position].collect
            if (collect == null) {
                setFacility.value = HttpConst.apiLocahost.setUserFacility(
                        userId, facilityId)
            } else {
                setFacility.value = HttpConst.apiLocahost.amendUserFacility(
                        userId, facilityId, collect)
            }

        } else {
            isLogin.value = false
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
package com.example.weathermvvm.ui.main.min.push

import androidx.lifecycle.MutableLiveData
import com.example.weathermvvm.base.BaseViewModel
import com.example.weathermvvm.bean.RealTimeBean
import com.example.weathermvvm.common.launch
import com.example.weathermvvm.network.HttpConst

class PushRegulateViewModel : BaseViewModel() {

    val realTimeBean = MutableLiveData<RealTimeBean>()
    lateinit var city: String

    fun getWeather(lat: Double, lng: Double) = launch({
        realTimeBean.value = HttpConst.apiColud.getRealtimeWeather(lng.toString(), lat.toString())
        loadState.value = false
    }, {
        loadState.value = false
    })

}

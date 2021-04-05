package com.example.weathermvvm.ui.main.home.weather

import androidx.lifecycle.MutableLiveData
import com.example.weathermvvm.base.BaseViewModel
import com.example.weathermvvm.bean.DailyBean
import com.example.weathermvvm.bean.RealTimeBean
import com.example.weathermvvm.common.launch
import com.example.weathermvvm.network.HttpConst

class WeatherViewModel : BaseViewModel() {
    val realTimeBean = MutableLiveData<RealTimeBean>()
    val dailyBean = MutableLiveData<DailyBean>()
    val refreshStatus = MutableLiveData<Boolean>()

    fun getRealTime(lat: String, lng: String, isRefresh: Boolean) = launch({
        loadState.value = true
        realTimeBean.value = HttpConst.apiColud.getRealtimeWeather(lng, lat)
        dailyBean.value = HttpConst.apiColud.getDailyWeather(lng, lat)
        if (isRefresh) refreshStatus.value = true
        loadState.value = false
        refreshStatus.value = false
    })
}

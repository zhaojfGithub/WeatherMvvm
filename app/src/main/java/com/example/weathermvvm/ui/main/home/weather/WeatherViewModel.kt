package com.example.weathermvvm.ui.main.home.weather

import androidx.lifecycle.MutableLiveData
import com.example.weathermvvm.base.BaseViewModel
import com.example.weathermvvm.bean.DailyBean
import com.example.weathermvvm.bean.RealTimeBean
import com.example.weathermvvm.common.launch
import com.example.weathermvvm.network.HttpConst

class WeatherViewModel :BaseViewModel(){
    val realTimeBean =MutableLiveData<RealTimeBean>()
    val dailyBean = MutableLiveData<DailyBean>()

    fun getRealTime(lat:String,lng:String)=launch({
        loadState.value = true
        realTimeBean.value = HttpConst.apiColud.getRealtimeWeather(lng, lat)
        dailyBean.value = HttpConst.apiColud.getDailyWeather(lng, lat)
        loadState.value = false
    })
}

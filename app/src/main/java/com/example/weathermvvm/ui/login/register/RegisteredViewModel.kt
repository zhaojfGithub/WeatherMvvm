package com.example.weathermvvm.ui.login.register

import androidx.lifecycle.MutableLiveData
import com.example.weathermvvm.base.BaseViewModel
import com.example.weathermvvm.bean.SignBean
import com.example.weathermvvm.common.launch
import com.example.weathermvvm.network.HttpConst

class RegisteredViewModel : BaseViewModel() {

    val signBean = MutableLiveData<SignBean>()

    fun setRegistered(account: String, password: String) = launch({
        loadState.value = true
        signBean.value=HttpConst.apiLocahost.registered(account.toLong(), password)
        loadState.value = false
    }, {
        loadState.value = false
    })
}
package com.example.weathermvvm.ui.login.login

import androidx.lifecycle.MutableLiveData
import com.example.weathermvvm.base.BaseViewModel
import com.example.weathermvvm.bean.UserBean
import com.example.weathermvvm.common.launch
import com.example.weathermvvm.network.HttpConst
import com.example.weathermvvm.store.LoginStore

class LoginViewModel : BaseViewModel() {

    val userBean = MutableLiveData<UserBean>()

    fun Login(account: String, password: String) = launch({
        loadState.value = true
        val list = HttpConst.apiLocahost.login(account.toLong(), password).apiData()
        LoginStore.setUserId(list[0].id.toString())
        userBean.value = list[0]
        loadState.value = false

    }, {
        loadState.value = false
    })
}
package com.example.weathermvvm.ui.main.min

import androidx.lifecycle.MutableLiveData
import com.example.weathermvvm.base.BaseViewModel
import com.example.weathermvvm.bean.UserBean
import com.example.weathermvvm.common.LiveBus
import com.example.weathermvvm.common.USER_LOGIN
import com.example.weathermvvm.common.launch
import com.example.weathermvvm.network.HttpConst
import com.example.weathermvvm.store.LoginStore

class MinViewModel : BaseViewModel() {

    val user = MutableLiveData<UserBean>()
    var isLogin: Boolean = false
    val loginData = MutableLiveData<Boolean>()
    val login = MutableLiveData<Boolean>()


    fun getUser() = launch({
        loadState.value = true
        val oneLogin = LoginStore.getUserId()
        if (LoginStore.isLogin()) {
            isLogin = true
            user.value = HttpConst.apiLocahost.user(oneLogin.toLong()).apiData()
        } else {
            isLogin = false
        }
        loadState.value = false
    }, {
        loadState.value = false
    })

    fun backLogin(){
        LoginStore.clearUser()
        LiveBus.post(USER_LOGIN,true)
        login.value = LoginStore.isLogin()
    }

    fun Login(){
        login.value = LoginStore.isLogin()
    }

}
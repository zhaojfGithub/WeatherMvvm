package com.example.weathermvvm.ui.login.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.example.weathermvvm.base.BaseViewModel
import com.example.weathermvvm.bean.UserBean
import com.example.weathermvvm.common.launch
import com.example.weathermvvm.network.HttpConst
import com.example.weathermvvm.store.LoginStore

class LoginViewModel @ViewModelInject constructor(
    private val loginRepository: LoginRepository
): BaseViewModel() {

    val userBean = MutableLiveData<UserBean>()

    fun setLogin(account: String, password: String) = launch({
        loadState.value = true
        val list = loginRepository.setLogin(account,password)
        LoginStore.setUserId(list[0].id.toString())
        userBean.value = list[0]
        loadState.value = false

    }, {
        loadState.value = false
    })
}
package com.example.weathermvvm.ui.main.min.user

import androidx.lifecycle.MutableLiveData
import com.example.weathermvvm.base.BaseViewModel
import com.example.weathermvvm.bean.SignBean
import com.example.weathermvvm.bean.UserBean
import com.example.weathermvvm.common.launch
import com.example.weathermvvm.network.HttpConst
import com.example.weathermvvm.store.LoginStore

class UserViewModel : BaseViewModel() {

    val userBean = MutableLiveData<UserBean>()
    val signBean = MutableLiveData<SignBean>()

    fun getUser() = launch({
        loadState.value = true
        val userId = LoginStore.getUserId().toLong()
        userBean.value = HttpConst.apiLocahost.user(userId).apiData()
        loadState.value = false
    }, {
        loadState.value = false
    }) {

    }

    fun updateUser() = launch({
        loadState.value = true
        signBean.value = HttpConst.apiLocahost.updateUser(userBean.value!!)
        loadState.value = false
    }, {
        loadState.value = false
    })

    fun update(type: String, str: String) {
        var user = userBean.value
        when (type) {
            "昵称" -> {
                user?.name = str
            }
            "邮箱" -> {
                user?.email = str
            }
            "密码" -> {
                user?.password=str
            }
        }
        userBean.value = user
    }

}
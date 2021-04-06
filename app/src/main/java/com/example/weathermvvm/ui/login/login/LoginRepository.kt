package com.example.weathermvvm.ui.login.login

import com.example.weathermvvm.network.HttpConst
import javax.inject.Inject

class LoginRepository @Inject constructor(){

    suspend fun setLogin(account: String, password: String) = HttpConst.apiLocahost.login(account.toLong(), password).apiData()

}
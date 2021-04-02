package com.example.weathermvvm.ui.main.min.opinion

import androidx.lifecycle.MutableLiveData
import com.example.weathermvvm.base.BaseViewModel
import com.example.weathermvvm.bean.SignBean
import com.example.weathermvvm.common.launch
import com.example.weathermvvm.network.HttpConst
import com.example.weathermvvm.store.LoginStore

class OpinionFeedbackViewModel : BaseViewModel() {

    val signBoolean = MutableLiveData<SignBean>()

    fun setOpinionFeedback(feedback: String) = launch({
        loadState.value = true
        signBoolean.value =
            HttpConst.apiLocahost.addFeedback(LoginStore.getUserId().toLong(), feedback)
        loadState.value = false
    }, {
        loadState.value = false
    })
}

package com.example.weathermvvm.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weathermvvm.common.launch

abstract class BaseViewModel : ViewModel() {

    val loadState = MutableLiveData<Boolean>()


}
package com.example.weathermvvm.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weathermvvm.bean.LoadState

abstract class BaseViewModel : ViewModel() {
    val loadState = MutableLiveData<LoadState>()
}
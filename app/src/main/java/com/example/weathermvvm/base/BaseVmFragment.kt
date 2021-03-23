package com.example.weathermvvm.base

import androidx.lifecycle.ViewModelProvider

abstract class BaseVmFragment<VM : BaseViewModel> : BaseFragment(){

    val mViewModel: VM by lazy { ViewModelProvider(this).get(viewModelClass()) }

    protected abstract fun viewModelClass(): Class<VM>
}
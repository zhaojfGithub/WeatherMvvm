package com.example.weathermvvm.base

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

abstract class BaseVmActivity<VM:BaseViewModel>:BaseActivity() {

    val mViewModel: VM by lazy { ViewModelProvider(this).get(viewModelClass()) }

    protected abstract fun viewModelClass(): Class<VM>

    override fun observe() {
        super.observe()
        mViewModel.loadState.observe(this, Observer {
            if (it) showDialog() else dismissDialog()
        })
    }
}
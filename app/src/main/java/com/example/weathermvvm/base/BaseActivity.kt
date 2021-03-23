package com.example.weathermvvm.base

import android.app.Dialog
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.example.weathermvvm.util.showProgress

abstract class BaseActivity : AppCompatActivity(){

    private var mProgressDialog: Dialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView(savedInstanceState)
        observe()
        if (savedInstanceState == null){
            initData()
        }
    }

    open fun initData(){}

    open fun observe(){}

    open fun initView(savedInstanceState: Bundle?){}

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    open fun showDialog(){
        dismissDialog()
        mProgressDialog = showProgress(this)
    }

    open fun dismissDialog(){
        mProgressDialog?.dismiss()
        mProgressDialog = null
    }
}
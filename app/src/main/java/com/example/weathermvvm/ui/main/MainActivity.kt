package com.example.weathermvvm.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weathermvvm.R
import com.example.weathermvvm.base.BaseActivity
import com.example.weathermvvm.base.BaseVmActivity
import com.example.weathermvvm.chmmon.ToastUtil
import com.example.weathermvvm.network.HttpConst
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseVmActivity<MainViewModel>() {


    override fun getLayoutId() = R.layout.activity_main

    override fun viewModelClass() = MainViewModel::class.java

    override fun initView(savedInstanceState: Bundle?) {
        button.setOnClickListener { mViewModel.getSiteList("中原福塔") }
    }

    override fun observe() {
        super.observe()
        mViewModel.run {
            data.observe(this@MainActivity, Observer { ToastUtil.showShort(it.query) })
            loadState.observe(this@MainActivity, Observer {
                if ( it ) showDialog() else dismissDialog()})
        }
    }

}
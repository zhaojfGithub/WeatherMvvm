package com.example.weathermvvm.ui.site.inquire

import android.os.Bundle
import com.example.weathermvvm.R
import com.example.weathermvvm.base.BaseVmActivity

class InquireSiteActivity :BaseVmActivity<InquireSiteViewModel>() {


    override fun viewModelClass()=InquireSiteViewModel::class.java

    override fun getLayoutId() = R.layout.activity_inquire_site

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
    }

    override fun observe() {
        super.observe()
    }
}
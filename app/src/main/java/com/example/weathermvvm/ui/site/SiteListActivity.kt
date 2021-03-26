package com.example.weathermvvm.ui.site

import com.example.weathermvvm.R
import com.example.weathermvvm.base.BaseVmActivity

class SiteListActivity : BaseVmActivity<SiteListViewModel>() {

    override fun viewModelClass()=SiteListViewModel::class.java

    override fun getLayoutId() = R.layout.activity_site_list
}
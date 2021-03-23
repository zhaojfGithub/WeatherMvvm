package com.example.weathermvvm.ui.main.min

import com.example.weathermvvm.R
import com.example.weathermvvm.base.BaseVmFragment


class MinFragment : BaseVmFragment<MinViewModel>() {

    override fun viewModelClass() = MinViewModel::class.java

    override fun getLayoutId() = R.layout.fragment_min

    companion object {
        fun newInstance() = MinFragment()
    }
}
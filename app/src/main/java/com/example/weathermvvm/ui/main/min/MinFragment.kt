package com.example.weathermvvm.ui.main.min

import android.content.Intent
import com.example.weathermvvm.MyApplication
import com.example.weathermvvm.R
import com.example.weathermvvm.base.BaseVmFragment
import com.example.weathermvvm.store.LoginStore
import com.example.weathermvvm.ui.login.login.LoginActivity
import com.example.weathermvvm.ui.login.register.RegisteredActivity
import com.example.weathermvvm.util.clearSpValue
import com.example.weathermvvm.util.removeSpValue
import kotlinx.android.synthetic.main.fragment_min.*


class MinFragment : BaseVmFragment<MinViewModel>() {

    override fun viewModelClass() = MinViewModel::class.java

    override fun getLayoutId() = R.layout.fragment_min

    companion object {
        fun newInstance() = MinFragment()
    }

    override fun initView() {
        super.initView()
        button.setOnClickListener {
            LoginStore.clearUserInfo()
        }
    }
}
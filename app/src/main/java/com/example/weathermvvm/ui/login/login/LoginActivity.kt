package com.example.weathermvvm.ui.login.login

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.lifecycle.Observer
import com.example.weathermvvm.R
import com.example.weathermvvm.base.BaseVmActivity
import com.example.weathermvvm.common.LiveBus
import com.example.weathermvvm.common.LogUtils
import com.example.weathermvvm.common.ToastUtil
import com.example.weathermvvm.common.USER_LOGIN
import com.example.weathermvvm.ui.login.register.RegisteredActivity
import com.example.weathermvvm.ui.main.MainActivity
import com.jeremyliao.liveeventbus.LiveEventBus
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.android.synthetic.main.activity_register.*

/**
 * 登录
 */

@AndroidEntryPoint
class LoginActivity  : BaseVmActivity<LoginViewModel>() {

    override fun viewModelClass() = LoginViewModel::class.java

    override fun getLayoutId() = R.layout.activity_register

    private var isPassword = false

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        bt_registered.text = "登录"
        iv_login.visibility = View.VISIBLE

        iv_password_or.setOnClickListener {
            LogUtils.v(tv_password.inputType.toString())
            if (isPassword) {
                isPassword = false
                tv_password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                iv_password_or.setImageResource(R.drawable.ic_password_hide)
            } else {
                isPassword = true
                tv_password.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                iv_password_or.setImageResource(R.drawable.ic_password_show)
            }
        }
        bt_registered.setOnClickListener {
            if (tv_account.text.toString().length==11){
                if (tv_password.text.toString().isNotEmpty()){
                    mViewModel.setLogin(tv_account.text.toString(),tv_password.text.toString())
                }else{
                    ToastUtil.showShort("请输入密码")
                }
            }else{
                ToastUtil.showShort("请输入正确的手机号")
            }
        }
        iv_login.setOnClickListener {
            startActivity(Intent(this,RegisteredActivity::class.java))
            finish()
        }
    }

    override fun observe() {
        super.observe()
        mViewModel.apply {
            userBean.observe(this@LoginActivity, Observer {
                LiveBus.post(USER_LOGIN,true)
                ToastUtil.showShort("登录成功")
                startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                finish()
            })
        }
    }

}
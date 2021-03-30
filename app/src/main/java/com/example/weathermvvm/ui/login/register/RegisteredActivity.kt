package com.example.weathermvvm.ui.login.register

import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.weathermvvm.R
import com.example.weathermvvm.base.BaseVmActivity
import com.example.weathermvvm.common.LogUtils
import com.example.weathermvvm.common.ToastUtil
import kotlinx.android.synthetic.main.activity_register.*
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * 注册
 */
class RegisteredActivity : BaseVmActivity<RegisteredViewModel>() {

    override fun viewModelClass() = RegisteredViewModel::class.java

    override fun getLayoutId() = R.layout.activity_register

    private var isPassword = false

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        bt_registered.text = "注册"
        iv_login.visibility = View.INVISIBLE
        LogUtils.v(tv_password.inputType.toString())
        iv_password_or.setOnClickListener {
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
            if (isChinaPhoneLegal(tv_account.text.toString())) {
                if (tv_password.text.toString().isNotEmpty()) {
                    mViewModel.setRegistered(tv_account.text.toString(), tv_password.text.toString())
                } else {
                    ToastUtil.showShort("请输入密码")
                }
            } else {
                ToastUtil.showShort("请输入正确的手机号")
            }
        }
        toolbar.apply {
            visibility = View.VISIBLE
            title = "注册"
            setTitleTextColor(ContextCompat.getColor(this@RegisteredActivity, R.color.white))
            setNavigationIcon(R.drawable.ic_back_64)
            setBackgroundColor(ContextCompat.getColor(this@RegisteredActivity, R.color.blue))
            setNavigationOnClickListener {
                this@RegisteredActivity.finish()
            }
        }
    }

    override fun observe() {
        super.observe()
        mViewModel.run {
            signBean.observe(this@RegisteredActivity, Observer {
                if (it.code == 200) {
                    finish()
                }
                ToastUtil.showShort(it.msg)
            })
        }
    }

    /**
     * 判断是否为手机号
     */
    private fun isChinaPhoneLegal(str: String): Boolean {
        val regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$"
        val p: Pattern = Pattern.compile(regExp)
        val m: Matcher = p.matcher(str)
        return m.matches()
    }
}
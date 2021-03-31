package com.example.weathermvvm.ui.main.min

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import com.example.weathermvvm.R
import com.example.weathermvvm.base.BaseActivity
import com.example.weathermvvm.base.BaseVmFragment
import com.example.weathermvvm.common.LiveBus
import com.example.weathermvvm.common.USER_LOGIN
import com.example.weathermvvm.common.USER_UPDATE
import com.example.weathermvvm.ui.login.login.LoginActivity
import com.example.weathermvvm.ui.main.home.HomeFragment
import com.example.weathermvvm.ui.main.min.opinion.OpinionFeedbackActivity
import com.example.weathermvvm.ui.main.min.push.PushRegulateActivity
import com.example.weathermvvm.ui.main.min.user.UserActivity
import com.example.weathermvvm.ui.site.SiteListActivity
import kotlinx.android.synthetic.main.fragment_min.*


class MinFragment : BaseVmFragment<MinViewModel>() {

    override fun viewModelClass() = MinViewModel::class.java

    override fun getLayoutId() = R.layout.fragment_min

    companion object {
        fun newInstance() = MinFragment()
    }

    override fun initView() {
        super.initView()
        rlTop.setOnClickListener { btOnclick(UserActivity::class.java) }
        tvFacilityCollect.setOnClickListener { }
        tvSiteRegulate.setOnClickListener { btOnclick(SiteListActivity::class.java) }
        tvPushRegulate.setOnClickListener { btOnclick(PushRegulateActivity::class.java) }
        tvOpinionFeedback.setOnClickListener { btOnclick(OpinionFeedbackActivity::class.java) }
        btBackLogin.setOnClickListener {
            mViewModel.backLogin()
        }
        btLogin.setOnClickListener { btOnclick(LoginActivity::class.java) }
    }

    override fun lazyLoadData() {
        super.lazyLoadData()
        mViewModel.getUser()
        mViewModel.Login()
    }


    override fun observe() {
        super.observe()
        mViewModel.apply {
            user.observe(this@MinFragment, Observer {
                if (it.name == null) {
                    tvName.text = it.accountNumber.toString()
                } else {
                    tvName.text = it.name
                }
                tvNumber.text = it.accountNumber.toString()
            })
            loginData.observe(this@MinFragment, Observer {
                if (it) {
                    getUser()
                }
            })
            login.observe(this@MinFragment, Observer {
                if (it) {
                    btBackLogin.visibility = View.VISIBLE
                    btLogin.visibility = View.GONE
                } else {
                    btBackLogin.visibility = View.GONE
                    btLogin.visibility = View.VISIBLE
                    tvName.text = ""
                    tvNumber.text = ""
                }
            })
        }
        LiveBus.observe<Boolean>(USER_LOGIN, this) {
            if (it) {
                mViewModel.getUser()
                mViewModel.Login()
            }
        }
        LiveBus.observe<Boolean>(USER_UPDATE,this){
            if (it){
                mViewModel.getUser()
                mViewModel.Login()
            }
        }
    }

    private fun btOnclick(java: Class<out Activity>) {
        if (mViewModel.isLogin) {
            startActivity(Intent(this.activity, java))
        } else {
            loginDialog()
        }
    }

    private fun loginDialog() {
        val dialog: AlertDialog.Builder = AlertDialog.Builder(activity)
                .setTitle("登录")
                .setMessage("请先进行登录！！！")
                .setPositiveButton("取消") { dialog1, _ ->
                    dialog1.dismiss()
                }
                .setPositiveButton("确定") { dialog2, _ ->
                    startActivity(Intent(activity, LoginActivity::class.java))
                    dialog2.dismiss()
                }
        dialog.show()
    }
}
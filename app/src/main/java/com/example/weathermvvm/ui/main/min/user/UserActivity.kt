package com.example.weathermvvm.ui.main.min.user

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.weathermvvm.R
import com.example.weathermvvm.base.BaseVmActivity
import com.example.weathermvvm.common.LiveBus
import com.example.weathermvvm.common.ToastUtil
import com.example.weathermvvm.common.USER_UPDATE
import kotlinx.android.synthetic.main.activity_site_list.toolbar
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : BaseVmActivity<UserViewModel>() {
    override fun viewModelClass() = UserViewModel::class.java

    override fun getLayoutId() = R.layout.activity_user

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        toolbar.apply {
            title = "个人信息"
            setTitleTextColor(ContextCompat.getColor(this@UserActivity, R.color.white))
            setNavigationIcon(R.drawable.ic_back_64)
            setBackgroundColor(ContextCompat.getColor(this@UserActivity, R.color.blue))
            setNavigationOnClickListener {
                this@UserActivity.finish()
            }
        }
        ivName.setOnClickListener { btOnclick("昵称") }
        //ivNumber.setOnClickListener { btOnclick("") }
        ivEmail.setOnClickListener { btOnclick("邮箱") }
        ivPassword.setOnClickListener { btOnclick("密码") }
        btUpdate.setOnClickListener { mViewModel.updateUser() }
    }

    override fun initData() {
        super.initData()
        mViewModel.getUser()
    }

    override fun observe() {
        super.observe()
        mViewModel.run {
            userBean.observe(this@UserActivity, Observer {
                if (it.name == null) tvUserName.text = it.accountNumber.toString() else tvUserName.text = it.name
                if (it.email == null) tvEmail.text = it.accountNumber.toString() else tvEmail.text = it.email
                tvPhone.text = it.accountNumber.toString()
                var str = ""
                for (i in it.password.indices) {
                    str = "$str*"
                }
                tvPassword.text = str
            })
            signBean.observe(this@UserActivity, Observer {
                if (it.code==200) {
                    ToastUtil.showShort(it.msg)
                    LiveBus.post(USER_UPDATE,true)
                }
            })
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun btOnclick(str: String) {
        val editText = AppCompatEditText(this)
        editText.isSingleLine=true
        val builder = AlertDialog.Builder(this).setTitle("请输入${str}").setView(editText)
                .setPositiveButton("取消") { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton("确定") { dialog, _ ->
                    if (editText.text.toString().isEmpty()) {
                        ToastUtil.showShort("不可为空，请输入!!!")
                    } else {
                        mViewModel.update(str, editText.text.toString())
                        dialog.dismiss()
                    }
                }
        builder.show()
    }
}

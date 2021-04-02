package com.example.weathermvvm.ui.main.min.opinion

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.weathermvvm.R
import com.example.weathermvvm.base.BaseVmActivity
import com.example.weathermvvm.common.ToastUtil
import kotlinx.android.synthetic.main.activity_opinion.*
import kotlinx.android.synthetic.main.activity_site_list.*
import kotlinx.android.synthetic.main.activity_site_list.toolbar

class OpinionFeedbackActivity : BaseVmActivity<OpinionFeedbackViewModel>() {
    override fun viewModelClass() = OpinionFeedbackViewModel::class.java

    override fun getLayoutId() = R.layout.activity_opinion

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        toolbar.apply {
            title = "意见反馈"
            setTitleTextColor(ContextCompat.getColor(this@OpinionFeedbackActivity, R.color.white))
            setNavigationIcon(R.drawable.ic_back_64)
            setBackgroundColor(ContextCompat.getColor(this@OpinionFeedbackActivity, R.color.blue))
            setNavigationOnClickListener { this@OpinionFeedbackActivity.finish() }
        }
        btSubmit.setOnClickListener {
            if (etFeedback.text.toString().isEmpty()) {
                ToastUtil.showShort("请先输入意见在进行提交!!!")
            } else {
                mViewModel.setOpinionFeedback(etFeedback.text.toString())
            }
        }
    }

    override fun observe() {
        super.observe()
        mViewModel.signBoolean.observe(this, Observer {
            ToastUtil.showShort(it.msg)
            etFeedback.setText("")
        })
    }
}
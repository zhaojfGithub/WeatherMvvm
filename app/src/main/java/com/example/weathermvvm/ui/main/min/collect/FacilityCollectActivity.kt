package com.example.weathermvvm.ui.main.min.collect

import android.app.AlertDialog
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weathermvvm.R
import com.example.weathermvvm.base.BaseVmActivity
import com.example.weathermvvm.common.LogUtils
import com.example.weathermvvm.common.ToastUtil
import kotlinx.android.synthetic.main.activity_site_list.toolbar
import kotlinx.android.synthetic.main.fragment_facility.*

class FacilityCollectActivity : BaseVmActivity<FacilityCollectViewModel>(){
    override fun viewModelClass() = FacilityCollectViewModel::class.java

    override fun getLayoutId() = R.layout.activity_facility_collect

    private lateinit var adapter: FacilityCollectAdapter

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        toolbar.apply {
            title = "设备收藏管理"
            setTitleTextColor(ContextCompat.getColor(this@FacilityCollectActivity, R.color.white))
            setNavigationIcon(R.drawable.ic_back_64)
            setBackgroundColor(ContextCompat.getColor(this@FacilityCollectActivity, R.color.blue))
            setNavigationOnClickListener {
                this@FacilityCollectActivity.finish() }
        }

        adapter = FacilityCollectAdapter()
        rv_facility.layoutManager = LinearLayoutManager(this)
        rv_facility.adapter = adapter
        adapter.setOnItemClickListener(object : FacilityCollectAdapter.OnItemClickListener {
            override fun onClick(position: Int,type: Int) {
                if (type == 1)
                    mViewModel.updateUserFacility(position)
                else
                    siteTextDialog(position)
            }
        })
    }

    override fun initData() {
        super.initData()
        mViewModel.getUserFacility()
    }

    override fun observe() {
        super.observe()
        mViewModel.apply {
            list.observe(this@FacilityCollectActivity, Observer {
                adapter.addAllList(it)
            })
            updateFacility.observe(this@FacilityCollectActivity, Observer {
                ToastUtil.showShort(it.msg)
                this.getUserFacility()
            })
        }
    }

    private fun siteTextDialog(position: Int) {
        val dialog: AlertDialog.Builder = AlertDialog.Builder(this)
                .setTitle("信息")
                .setMessage(mViewModel.list.value?.get(position)?.text)
                .setPositiveButton("确定") { dialog2, _ ->
                    dialog2.dismiss()
                }
        dialog.show()
    }
}
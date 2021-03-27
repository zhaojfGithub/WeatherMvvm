package com.example.weathermvvm.ui.site

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weathermvvm.R
import com.example.weathermvvm.base.BaseVmActivity
import com.example.weathermvvm.common.ToastUtil
import com.example.weathermvvm.ui.site.inquire.InquireSiteActivity
import kotlinx.android.synthetic.main.activity_site_list.*


class SiteListActivity : BaseVmActivity<SiteListViewModel>() {

    override fun viewModelClass() = SiteListViewModel::class.java

    private lateinit var adapter: SiteListAdapter
    private  var isAmend: Boolean = false

    override fun getLayoutId() = R.layout.activity_site_list

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        toolbar.apply {
            title = "城市列表"
            setTitleTextColor(ContextCompat.getColor(this@SiteListActivity, R.color.white))
            setNavigationIcon(R.drawable.ic_back_64)
            setBackgroundColor(ContextCompat.getColor(this@SiteListActivity, R.color.blue))
            setNavigationOnClickListener {
                setResult(Activity.RESULT_OK,intent)
                this@SiteListActivity.finish() }
        }
        rvList.layoutManager = LinearLayoutManager(this)
        adapter = SiteListAdapter()
        rvList.adapter = adapter
        adapter.setOnItemClickListener(object : SiteListAdapter.OnItemClickListener {
            override fun onClick(position: Int) {
                onClickFooter(position)
            }
        })
        adapter.setOnItemLongClickListener(object : SiteListAdapter.OnItemLongClickListener {
            override fun onLongClick(id: Long?, position: Int) {
                onLongClickFooter(id, position)
            }
        })
    }

    override fun initData() {
        super.initData()
        mViewModel.getSiteList()
    }

    override fun observe() {
        super.observe()
        mViewModel.run {
            getList.observe(this@SiteListActivity, Observer {
                adapter.addAllList(it)
                adapter.notifyDataSetChanged()
            })
            setList.observe(this@SiteListActivity, Observer {
                mViewModel.getSiteList()
                isAmend = true
                ToastUtil.showShort(it.msg)
            })
        }
    }

    private fun onClickFooter(position: Int) {
        if (position == -1) {
            startActivityForResult(Intent(this@SiteListActivity, InquireSiteActivity::class.java), 1)
        } else if (position == -2) {
            val dialog = AlertDialog.Builder(this)
                    .setTitle("编辑地址")
                    .setMessage("长按item即可删除")
                    .setPositiveButton("确定") { dialog1: DialogInterface, _: Int ->
                        dialog1.dismiss()
                    }
            dialog.show()
        }
    }

    private fun onLongClickFooter(id: Long?, position: Int) {
        val dialog: AlertDialog.Builder = AlertDialog.Builder(this)
                .setTitle("删除地址")
                .setMessage("确定删除这个地址吗？")
                .setPositiveButton("确定") { dialog1, _ ->
                    mViewModel.removeSiteList(id, position)
                    dialog1.dismiss()
                }
        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val name = data?.getStringExtra("name")
            val lat = data?.getStringExtra("lat")
            val lng = data?.getStringExtra("lng")
            if (name != null && lat != null && lng != null) {
                mViewModel.setSiteList(name, lat, lng)
            }
        }
    }
}
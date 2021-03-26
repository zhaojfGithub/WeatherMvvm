package com.example.weathermvvm.ui.site

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weathermvvm.R
import com.example.weathermvvm.base.BaseVmActivity
import com.example.weathermvvm.common.ToastUtil
import kotlinx.android.synthetic.main.activity_site_list.*


class SiteListActivity : BaseVmActivity<SiteListViewModel>() {

    override fun viewModelClass() = SiteListViewModel::class.java

    private lateinit var adapter: SiteListAdapter

    override fun getLayoutId() = R.layout.activity_site_list

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        toolbar.apply {
            title = "城市列表"
            setTitleTextColor(ContextCompat.getColor(this@SiteListActivity, R.color.white))
            setNavigationIcon(R.drawable.ic_back_foreground)
            setBackgroundColor(ContextCompat.getColor(this@SiteListActivity, R.color.blue))
            setNavigationOnClickListener { this@SiteListActivity.finish() }
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
                onLongClickFooter(id,position)
            }
        })
    }

    override fun initData() {
        super.initData()
        mViewModel.getSiteList()
    }

    private fun onClickFooter(position: Int) {
        if (position == -1) {
            this.finish()
        } else if (position == -2) {
            val dialog = AlertDialog.Builder(this)
                    .setTitle("编辑地址")
                    .setMessage("长按item即可删除")
                    .setPositiveButton("确定") { dialog1: DialogInterface, which: Int ->
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
                    mViewModel.removeSiteList(id,position)
                    dialog1.dismiss()
                }
        dialog.show()
    }

    override fun observe() {
        super.observe()
        mViewModel.run {
            getList.observe(this@SiteListActivity, Observer {
                adapter.addAllList(it)
                adapter.notifyDataSetChanged()
            })
            setList.observe(this@SiteListActivity, Observer {
                ToastUtil.showShort(it.msg)
            })
            loadState.observe(this@SiteListActivity, Observer {
                if (it) showDialog() else dismissDialog()
            })
        }
    }
}
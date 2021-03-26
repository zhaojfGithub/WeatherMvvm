package com.example.weathermvvm.ui.site.inquire

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weathermvvm.R
import com.example.weathermvvm.base.BaseVmActivity
import kotlinx.android.synthetic.main.activity_inquire_site.*

class InquireSiteActivity : BaseVmActivity<InquireSiteViewModel>() {


    override fun viewModelClass() = InquireSiteViewModel::class.java

    override fun getLayoutId() = R.layout.activity_inquire_site

    private lateinit var adapter: InquireSiteAdapter

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        iv_back.setOnClickListener { finish() }
        ivClearSearch.setOnClickListener { acetInput.setText("") }
        ivDone.setOnClickListener{ mViewModel.getSiteList(acetInput.text.toString()) }
        rv_inquire.layoutManager = LinearLayoutManager(this)
        adapter = InquireSiteAdapter()
        rv_inquire.adapter = adapter
        adapter.setOnItemClickListener(object : InquireSiteAdapter.OnItemClickListener{
            override fun onClick(Name: String, Lat: String, Lng: String) {
                val intent = intent
                intent.putExtra("name",Name)
                intent.putExtra("lat",Lat)
                intent.putExtra("lng",Lng)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }
        })
    }

    override fun observe() {
        super.observe()
        mViewModel.run {
            siteBean.observe(this@InquireSiteActivity, Observer {
                adapter.addAllList(it)
            })
        }
    }
}
package com.example.weathermvvm.ui.main.home

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.view.View
import androidx.lifecycle.Observer
import com.example.weathermvvm.R
import com.example.weathermvvm.base.BaseVmFragment
import com.example.weathermvvm.ui.main.home.weather.WeatherViewPageAdapter
import com.example.weathermvvm.ui.site.SiteListActivity
import com.example.weathermvvm.ui.site.inquire.InquireSiteActivity
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseVmFragment<HomeViewModel>() {

    override fun viewModelClass() = HomeViewModel::class.java

    override fun getLayoutId() = R.layout.fragment_home

    private lateinit var adapter: WeatherViewPageAdapter

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun initView() {
        super.initView()
        adapter = WeatherViewPageAdapter(this)
        viewPage.adapter = adapter
        val decorView = requireActivity().window.decorView
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        requireActivity().window.statusBarColor = Color.TRANSPARENT
    }

    override fun lazyLoadData() {
        super.lazyLoadData()
        mViewModel.getSiteList()
    }

    override fun observe() {
        super.observe()
        mViewModel.run {
            allSiteBean.observe(this@HomeFragment, Observer {
                adapter.addAllFragment(it)
                adapter.notifyDataSetChanged()
            })
            isSite.observe(this@HomeFragment, Observer {
                if (it) {
                    addSiteDialog()
                }
            })
        }
    }

    private fun addSiteDialog() {
        val dialog: AlertDialog.Builder = AlertDialog.Builder(activity)
                .setTitle("添加地址")
                .setMessage("您还有没有添加地址，是否现在去添加？")
                .setPositiveButton("确定") { dialog1, _ ->
                    startActivityForResult(Intent(activity, SiteListActivity::class.java),1)
                    dialog1.dismiss()
                }
        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            mViewModel.getSiteList()
        }
    }

}
package com.example.weathermvvm.ui.main.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.weathermvvm.R
import com.example.weathermvvm.base.BaseVmFragment
import com.example.weathermvvm.ui.main.home.weather.WeatherViewPageAdapter
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
        }
    }


}
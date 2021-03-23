package com.example.weathermvvm.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weathermvvm.R
import com.example.weathermvvm.base.BaseFragment
import com.example.weathermvvm.base.BaseVmFragment


class HomeFragment : BaseVmFragment<HomeViewModel>() {

    override fun viewModelClass() = HomeViewModel::class.java

    override fun getLayoutId() = R.layout.fragment_home

    companion object {
        fun newInstance() = HomeFragment()
    }

}
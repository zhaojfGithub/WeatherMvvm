package com.example.weathermvvm.ui.main.min

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weathermvvm.R
import com.example.weathermvvm.base.BaseFragment
import com.example.weathermvvm.base.BaseVmFragment


class MinFragment : BaseVmFragment<MinViewModel>() {

    override fun viewModelClass() = MinViewModel::class.java

    override fun getLayoutId() = R.layout.fragment_min

    companion object {
        fun newInstance() = MinFragment()
    }
}
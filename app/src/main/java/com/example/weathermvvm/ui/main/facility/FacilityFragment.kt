package com.example.weathermvvm.ui.main.facility

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weathermvvm.R
import com.example.weathermvvm.base.BaseFragment
import com.example.weathermvvm.base.BaseVmFragment


class FacilityFragment : BaseVmFragment<FacilityViewModel>() {

    override fun viewModelClass() = FacilityViewModel::class.java

    override fun getLayoutId() = R.layout.fragment_facility

    companion object {
        fun newInstance() = FacilityFragment()
    }

}
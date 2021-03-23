package com.example.weathermvvm.ui.main.facility


import com.example.weathermvvm.R
import com.example.weathermvvm.base.BaseVmFragment


class FacilityFragment : BaseVmFragment<FacilityViewModel>() {

    override fun viewModelClass() = FacilityViewModel::class.java

    override fun getLayoutId() = R.layout.fragment_facility

    companion object {
        fun newInstance() = FacilityFragment()
    }

}
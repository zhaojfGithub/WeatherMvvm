package com.example.weathermvvm.ui.main.home.weather

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.weathermvvm.bean.AllSiteBean

class WeatherViewPageAdapter(fragment: Fragment):FragmentStateAdapter(fragment){

    private var list = ArrayList<AllSiteBean>()

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return WeatherFragment.newInstance(list[position].lat,list[position].lng,list[position].site)
    }

    fun addAllFragment(list: List<AllSiteBean>){
        this.list.clear()
        this.list.addAll(list)
    }

    fun clearFragment(number: Int){
        list.clear()
    }

    fun addFragment(data: AllSiteBean){
        list.add(data)
    }

}
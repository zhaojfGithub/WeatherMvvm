package com.example.weathermvvm.ui.main.home.weather

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.weathermvvm.bean.AllSiteBean

class WeatherViewPageAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private var list = ArrayList<AllSiteBean>()
    private var listItemId = ArrayList<Long>()


    override fun getItemCount(): Int {
        return list.size
    }


    override fun createFragment(position: Int): Fragment {
        return WeatherFragment.newInstance(list[position].lat, list[position].lng, list[position].site)
    }

    override fun getItemId(position: Int): Long {
        val index = list[position].lat.indexOf(".") + 1
        val itemId = list[position].lat.substring(index, list[position].lat.length).toLong()
        listItemId.add(itemId)
        return itemId
    }

    override fun containsItem(itemId: Long): Boolean {
        return listItemId.contains(itemId)
    }

    fun addAllFragment(list: List<AllSiteBean>) {
        this.list.clear()
        this.list.addAll(list)
    }


    fun addFragment(data: AllSiteBean) {
        list.add(data)
    }

}
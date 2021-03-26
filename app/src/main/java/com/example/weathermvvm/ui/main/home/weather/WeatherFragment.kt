package com.example.weathermvvm.ui.main.home.weather

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.weathermvvm.R
import com.example.weathermvvm.base.BaseVmFragment
import com.example.weathermvvm.ui.site.SiteListActivity
import kotlinx.android.synthetic.main.fragment_facility.*
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.android.synthetic.main.life_index.*
import java.text.SimpleDateFormat
import java.util.*

class WeatherFragment : BaseVmFragment<WeatherViewModel>() {
    override fun viewModelClass() = WeatherViewModel::class.java

    override fun getLayoutId() = R.layout.fragment_weather

    private var lat: String? =null
    private var lng: String? =null
    private var site: String? =null

    companion object {
        fun newInstance(lat: String, lng: String, site: String) = WeatherFragment().apply {
            arguments = Bundle().apply {
                putString("lat", lat)
                putString("lng", lng)
                putString("site",site)
            }
        }

    }

    override fun initView() {
        super.initView()
        arguments?.let {
            lat = it.getString("lat")
            lng = it.getString("lng")
            site=it.getString("site")
        }
        tv_title.text = site
        iv_location.setOnClickListener {
            startActivity(Intent(requireActivity(),SiteListActivity::class.java))
        }
    }

    override fun lazyLoadData() {
        super.lazyLoadData()
        mViewModel.getRealTime(lat!!, lng!!)
    }

    override fun observe() {
        super.observe()
        mViewModel.run {
            realTimeBean.observe(this@WeatherFragment, Observer {
                val currentTempText="${it.result.realtime.temperature.toInt()} ℃"
                currentTemp.text = currentTempText
                currentSky.text = getSky(it.result.realtime.skycon).info
                val currentPM25Text = "空气指数 ${it.result.realtime.air_quality.aqi.chn.toInt()}"
                currentAQI.text = currentPM25Text
                nowLayout.setBackgroundResource(getSky(it.result.realtime.skycon).bg)

            })
            dailyBean.observe(this@WeatherFragment, Observer {
                forecastLayout.removeAllViews()
                val days = it.result.daily.skycon.size
                for (i in 0 until days) {
                    val skyCon = it.result.daily.skycon[i]
                    val temperature = it.result.daily.temperature[i]
                    val view = LayoutInflater.from(requireActivity()).inflate(R.layout.forecast_item, forecastLayout, false)
                    val dateInfo = view.findViewById(R.id.dateInfo) as TextView
                    val skyIcon = view.findViewById(R.id.skyIcon) as ImageView
                    val skyInfo = view.findViewById(R.id.skyInfo) as TextView
                    val temperatureInfo = view.findViewById(R.id.temperatureInfo) as TextView
                    dateInfo.text = skyCon.date.substring(0,10)
                    val sky = getSky(skyCon.value)
                    skyIcon.setImageResource(sky.icon)
                    skyInfo.text = sky.info
                    val tempText = "${temperature.min.toInt()} ~ ${temperature.max.toInt()} ℃"
                    temperatureInfo.text = tempText
                    forecastLayout.addView(view)
                }

                val lifeIndex = it.result.daily.life_index
                coldRiskText.text = lifeIndex.coldRisk[0].desc
                dressingText.text = lifeIndex.dressing[0].desc
                ultravioletText.text = lifeIndex.ultraviolet[0].desc
                carWashingText.text = lifeIndex.carWashing[0].desc
                weatherLayout.visibility = View.VISIBLE
            })
            loadState.observe(this@WeatherFragment, Observer {
                if (it) showDialog() else dismissProgress()
            })
        }
    }
}
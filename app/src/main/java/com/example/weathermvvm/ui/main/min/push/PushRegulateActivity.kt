package com.example.weathermvvm.ui.main.min.push

import android.Manifest
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.example.weathermvvm.R
import com.example.weathermvvm.base.BaseVmActivity
import com.example.weathermvvm.common.ToastUtil
import com.example.weathermvvm.ui.login.login.LoginActivity
import com.example.weathermvvm.ui.main.home.weather.getSky
import kotlinx.android.synthetic.main.activity_push_regulate.*
import kotlinx.android.synthetic.main.activity_site_list.toolbar


class PushRegulateActivity : BaseVmActivity<PushRegulateViewModel>(), (AMapLocation) -> Unit {

    lateinit var mLocationClient: AMapLocationClient
    lateinit var mLocationOption: AMapLocationClientOption
    lateinit var manager: NotificationManager
    lateinit var channel: NotificationChannel


    override fun viewModelClass() = PushRegulateViewModel::class.java

    override fun getLayoutId() = R.layout.activity_push_regulate

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        toolbar.apply {
            title = "通知"
            setTitleTextColor(ContextCompat.getColor(this@PushRegulateActivity, R.color.white))
            setNavigationIcon(R.drawable.ic_back_64)
            setBackgroundColor(ContextCompat.getColor(this@PushRegulateActivity, R.color.blue))
            setNavigationOnClickListener { this@PushRegulateActivity.finish() }
        }
        manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel =
                NotificationChannel("normal", "Normal", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        btLocationData.setOnClickListener { applyPermission() }
        btPushInform.setOnClickListener { pushInform() }
    }

    override fun observe() {
        super.observe()
        mViewModel.apply {
            realTimeBean.observe(this@PushRegulateActivity, Observer {
                val city = "城市:${this.city}\n"
                val weather = "天气:${getSky(it.result.realtime.skycon).info}\n"
                val temperature = "温度:${it.result.realtime.temperature}\n"
                val speed = "风速:${it.result.realtime.wind.speed}\n"
                val site = city + weather + temperature + speed
                tvSite.text = site
            })
        }
    }

    private fun pushInform() {
        val it = mViewModel.realTimeBean.value
        if (it == null) {
            ToastUtil.showShort("请先获取数据")
        } else {
            val city = "城市:${mViewModel.city}\n"
            val weather = "天气:${getSky(it.result.realtime.skycon).info}\n"
            val temperature = "温度:${it.result.realtime.temperature}\n"
            val speed = "风速:${it.result.realtime.wind.speed}\n"
            val site = city + weather + temperature + speed
            val notification = NotificationCompat.Builder(this, "normal")
                .setContentTitle("Zhu-趣天气")
                .setContentText(site)
                .setSmallIcon(getSky(weather).icon)
                .setLargeIcon(BitmapFactory.decodeResource(resources, getSky(weather).icon))
                .setAutoCancel(true)
                .build()
            manager.notify(1, notification)
        }
    }

    private fun initLocation() {
        val mLocationListener = AMapLocationListener(this)
        mLocationClient = AMapLocationClient(this)
        mLocationClient.setLocationListener(mLocationListener)
        mLocationOption = AMapLocationClientOption()
        mLocationOption.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
        mLocationOption.isOnceLocationLatest = true
        mLocationOption.isNeedAddress = true
        mLocationOption.isMockEnable = true
        mLocationOption.httpTimeOut = 8000
        mLocationOption.isOnceLocation = true
        mLocationOption.isLocationCacheEnable = false
        mLocationClient.setLocationOption(mLocationOption)
        mLocationClient.startLocation()
        mViewModel.loadState.value = true
    }

    override fun invoke(p1: AMapLocation) {
        if (p1.errorCode == 0) {
            mViewModel.getWeather(p1.latitude, p1.longitude)
            mViewModel.city = p1.city
        } else {
            ToastUtil.showShort("定位失败${p1.errorCode},errInfo:${p1.errorInfo}")
        }

    }

    private fun applyPermission() {
        //检查是否有定位权限
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            initLocation()
        } else {
            requestCameraPermission()
        }
    }

    private fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )

        ) {
            loginDialog()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                0
            )
        }

    }

    private fun toSelfSetting(){
        val intent = Intent(Settings.ACTION_SETTINGS)
        this.startActivity(intent)
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults.size==1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            //已经给与权限
            initLocation()
        }else{
            loginDialog()
        }
    }

    private fun loginDialog() {
        val dialog: AlertDialog.Builder = AlertDialog.Builder(this)
            .setTitle("提示")
            .setMessage("该功能必须给与权限才可以使用！！！")
            .setPositiveButton("取消") { dialog1, _ ->
                dialog1.dismiss()
            }
            .setPositiveButton("确定") { dialog2, _ ->
                toSelfSetting()
                dialog2.dismiss()
            }
        dialog.show()
    }
}
package com.example.weathermvvm.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weathermvvm.R
import com.example.weathermvvm.chmmon.ToastUtil
import com.example.weathermvvm.network.HttpConst
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mViewModel.data.observe(this, Observer { ToastUtil.showShort(it.query) })
        mViewModel.loadState.observe(this, Observer { textView.text=it.msg })
        button.setOnClickListener { mViewModel.getSiteList("中原福塔") }
    }
}
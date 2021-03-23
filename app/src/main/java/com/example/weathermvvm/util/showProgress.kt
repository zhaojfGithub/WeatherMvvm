package com.example.weathermvvm.util

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.example.weathermvvm.R

@SuppressLint("InflateParams")
fun showProgress(context: Context):Dialog {
    val view:View = LayoutInflater.from(context).inflate(
        R.layout.layout_dialog_progress,null
    )
    return Dialog(context,R.style.Dialog_Progress).apply {
        setContentView(view)
        setCancelable(false)
        show()
    }
}
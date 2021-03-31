package com.example.weathermvvm.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.jeremyliao.liveeventbus.LiveEventBus

object LiveBus {

    inline fun <reified T> post(channel: String, value: T) =
        LiveEventBus.get(channel, T::class.java).post(value)

    inline fun <reified T> postAcrossApp(channel: String, value: T) =
        LiveEventBus.get(channel,T::class.java).postAcrossApp(value)

    inline fun <reified T> observe(
        channel: String,
        owner: LifecycleOwner,
        crossinline observer: ((value: T) -> Unit)
    ) =
        LiveEventBus.get(channel, T::class.java).observe(owner, Observer { observer(it) })
}
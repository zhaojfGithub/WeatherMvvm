package com.example.weathermvvm.bean

data class AllSiteBean(
        val gmtCreate: String?,
        val gmtModified: String?,
        val id: Long?,
        val isDeleted: Int?,
        val lat: String,
        val lng: String,
        val site: String,
        val userId: Long?
)
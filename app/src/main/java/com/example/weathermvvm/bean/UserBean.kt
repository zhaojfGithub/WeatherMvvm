package com.example.weathermvvm.bean

data class UserBean(
        val accountNumber: Long,
        var email: String?,
        val gmtCreate: String,
        val gmtModified: String,
        val id: Long,
        val isDeleted: Int,
        var name: String?,
        var password: String
)
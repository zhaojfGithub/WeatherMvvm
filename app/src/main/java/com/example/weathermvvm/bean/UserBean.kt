package com.example.weathermvvm.bean

data class UserBean(
    val accountNumber: Long,
    val email: String?,
    val gmtCreate: String,
    val gmtModified: String,
    val id: Long,
    val isDeleted: Int,
    val name: String?,
    val password: String
)
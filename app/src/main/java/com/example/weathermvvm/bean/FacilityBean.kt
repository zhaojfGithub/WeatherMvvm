package com.example.weathermvvm.bean

data class FacilityBean(
        val facilityId: Long,
        val facilitySite: String,
        val facilityUuid: String,
        val gmtCreate: String,
        val gmtModified: String,
        var collect: Int?
)
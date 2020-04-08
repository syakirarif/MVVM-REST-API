package com.arifudesu.mvvmrestapi.model.detail


import com.google.gson.annotations.SerializedName

data class To(
    @SerializedName("day")
    val day: Int? = 0,
    @SerializedName("month")
    val month: Int? = 0,
    @SerializedName("year")
    val year: Int? = 0
)
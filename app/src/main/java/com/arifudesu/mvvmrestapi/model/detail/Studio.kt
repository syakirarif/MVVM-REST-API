package com.arifudesu.mvvmrestapi.model.detail


import com.google.gson.annotations.SerializedName

data class Studio(
    @SerializedName("mal_id")
    val malId: Int? = 0,
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("url")
    val url: String? = ""
)
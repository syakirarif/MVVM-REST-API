package com.arifudesu.mvvmrestapi.model.detail


import com.google.gson.annotations.SerializedName

data class Aired(
    @SerializedName("from")
    val from: String? = "",
    @SerializedName("to")
    val to: String? = "",
    @SerializedName("prop")
    val prop: Prop? = Prop(),
    @SerializedName("string")
    val string: String? = ""
)
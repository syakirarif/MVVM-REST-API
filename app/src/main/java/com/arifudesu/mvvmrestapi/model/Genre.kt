package com.arifudesu.mvvmrestapi.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class Genre(
    @ColumnInfo(name = "malId")
    @SerializedName("mal_id")
    val malId: Int? = 0,

    @ColumnInfo(name = "type")
    @SerializedName("type")
    val type: String? = "",

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String? = "",

    @ColumnInfo(name = "url")
    @SerializedName("url")
    val url: String? = ""
)
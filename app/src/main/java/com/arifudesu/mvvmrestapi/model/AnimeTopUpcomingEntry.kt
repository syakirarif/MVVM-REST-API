package com.arifudesu.mvvmrestapi.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tb_anime_top_upcoming")
data class AnimeTopUpcomingEntry(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @ColumnInfo(name = "malId")
    @SerializedName("mal_id")
    val malId: Int? = 0,

    @ColumnInfo(name = "rank")
    @SerializedName("rank")
    val rank: Int? = 0,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String? = "",

    @ColumnInfo(name = "url")
    @SerializedName("url")
    val url: String? = "",

    @ColumnInfo(name = "imageUrl")
    @SerializedName("image_url")
    val imageUrl: String? = "",

    @ColumnInfo(name = "members")
    @SerializedName("members")
    val members: Int? = 0,

    @ColumnInfo(name = "score")
    @SerializedName("score")
    val score: Double? = 0.0
)
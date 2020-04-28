package com.arifudesu.mvvmrestapi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_anime_favorite")
data class AnimeFavoriteEntry (

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @ColumnInfo(name = "malId")
    val malId: Int? = 0,

    @ColumnInfo(name = "title")
    val title: String? = "",

    @ColumnInfo(name = "url")
    val url: String? = "",

    @ColumnInfo(name = "imageUrl")
    val imageUrl: String? = "",

    @ColumnInfo(name = "type")
    val type: String? = ""
)
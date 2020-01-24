package com.arifudesu.mvvmrestapi.model


import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tb_anime")
data class AnimeEntry(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @ColumnInfo(name = "malId")
    @SerializedName("mal_id")
    val malId: Int? = 0,

    @ColumnInfo(name = "url")
    @SerializedName("url")
    val url: String? = "",

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String? = "",

    @ColumnInfo(name = "imageUrl")
    @SerializedName("image_url")
    val imageUrl: String? = "",

    @ColumnInfo(name = "synopsis")
    @SerializedName("synopsis")
    val synopsis: String? = "",

    @ColumnInfo(name = "type")
    @SerializedName("type")
    val type: String? = "",

    @ColumnInfo(name = "airingStart")
    @SerializedName("airing_start")
    val airingStart: String? = "",

    @ColumnInfo(name = "episodes")
    @SerializedName("episodes")
    val episodes: Int? = 0,

    @ColumnInfo(name = "members")
    @SerializedName("members")
    val members: Int? = 0,

    @SerializedName("genres")
    val genres: List<Genre>?,

    @ColumnInfo(name = "source")
    @SerializedName("source")
    val source: String? = "",

    @SerializedName("producers")
    val producers: List<Producer>?,

    @ColumnInfo(name = "score")
    @SerializedName("score")
    val score: Double? = 0.0,

//    @Embedded
//    @SerializedName("licensors")
//    val licensors: List<String?>? = listOf(),

    @ColumnInfo(name = "r18")
    @SerializedName("r18")
    val r18: Boolean? = false,

    @ColumnInfo(name = "kids")
    @SerializedName("kids")
    val kids: Boolean? = false,

    @ColumnInfo(name = "continuing")
    @SerializedName("continuing")
    val continuing: Boolean? = false
)
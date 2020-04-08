package com.arifudesu.mvvmrestapi.model.detail


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tb_anime_detail")
data class DetailAnimeEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @SerializedName("mal_id")
    val malId: Int? = 0,

    @SerializedName("url")
    val url: String? = "",

    @SerializedName("image_url")
    val imageUrl: String? = "",

    @SerializedName("trailer_url")
    val trailerUrl: String? = "",

    @SerializedName("title")
    val title: String? = "",

    @SerializedName("title_english")
    val titleEnglish: String? = "",

    @SerializedName("title_japanese")
    val titleJapanese: String? = "",

//    @SerializedName("title_synonyms")
//    val titleSynonyms: List<Any>? = listOf(),

    @SerializedName("type")
    val type: String? = "",

    @SerializedName("source")
    val source: String? = "",

    @SerializedName("episodes")
    val episodes: Int? = 0,

    @SerializedName("status")
    val status: String? = "",

    @SerializedName("airing")
    val airing: Boolean? = false,

//    @SerializedName("aired")
//    val aired: Aired? = Aired(),

    @SerializedName("duration")
    val duration: String? = "",

    @SerializedName("rating")
    val rating: String? = "",

    @SerializedName("score")
    val score: Double? = 0.0,

    @SerializedName("scored_by")
    val scoredBy: Int? = 0,

    @SerializedName("rank")
    val rank: Int? = 0,

    @SerializedName("popularity")
    val popularity: Int? = 0,

    @SerializedName("members")
    val members: Int? = 0,

    @SerializedName("favorites")
    val favorites: Int? = 0,

    @SerializedName("synopsis")
    val synopsis: String? = "",

//    @SerializedName("background")
//    val background: Any? = Any(),

    @SerializedName("premiered")
    val premiered: String? = "",

    @SerializedName("broadcast")
    val broadcast: String? = ""

//    @SerializedName("producers")
//    val producers: List<Producer>? = listOf(),
//
//    @SerializedName("licensors")
//    val licensors: List<Licensor>? = listOf(),
//
//    @SerializedName("studios")
//    val studios: List<Studio>? = listOf(),
//
//    @SerializedName("genres")
//    val genres: List<Genre>? = listOf(),
//
//    @SerializedName("opening_themes")
//    val openingThemes: List<String>? = listOf(),
//
//    @SerializedName("ending_themes")
//    val endingThemes: List<String>? = listOf()
)
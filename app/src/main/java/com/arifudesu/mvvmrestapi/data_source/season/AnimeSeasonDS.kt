package com.arifudesu.mvvmrestapi.data_source.season

import com.arifudesu.mvvmrestapi.model.AnimeEntry

interface AnimeSeasonDS {

    fun getAnimeSeason(
        seasonYear: String,
        seasonName: String,
        isRefresh: Boolean = false,
        callback: GetCallback
    )

    fun saveAnimeSeason(entry: List<AnimeEntry>)

//    fun insertAnimeFavorite(entry: AnimeEntry)

//    fun checkAnimeFavorite(malId: String, callback: GetFavoriteCallback)

    interface GetCallback {
        fun onLoaded(entry: List<AnimeEntry>)
        fun onError(errorMessage: String?)
        fun onFinished()
    }

//    interface GetFavoriteCallback {
//        fun onLoaded(count: Int)
//        fun onError(errorMessage: String?)
//        fun onFinished()
//    }
}
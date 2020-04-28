package com.arifudesu.mvvmrestapi.data_source.detail

import com.arifudesu.mvvmrestapi.model.AnimeFavoriteEntry
import com.arifudesu.mvvmrestapi.model.detail.DetailAnimeEntry

interface DetailAnimeDS {

    fun getDetailAnime(
        malId: String,
        callback: GetCallback
    )

    fun saveDetailAnime(entry: DetailAnimeEntry)

//    fun checkAnimeFavorite(malId: String, callback: GetCallbackFavorite)
//
//    fun saveAnimeFavorite(entry: AnimeFavoriteEntry)
//
//    fun removeAnimeFavorite(malId: String)

    interface GetCallback {
        fun onLoaded(entry: DetailAnimeEntry)
        fun onError(errorMessage: String?)
        fun onFinished()
    }

//    interface GetCallbackFavorite {
//        fun onLoaded(entry: AnimeFavoriteEntry)
//        fun onError(errorMessage: String?)
//        fun onFinished()
//    }
}
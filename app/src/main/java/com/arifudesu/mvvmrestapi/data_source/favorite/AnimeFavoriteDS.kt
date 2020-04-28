package com.arifudesu.mvvmrestapi.data_source.favorite

import com.arifudesu.mvvmrestapi.model.AnimeFavoriteEntry

interface AnimeFavoriteDS {

    fun getAnimeFavorite(
        callback: GetCallback
    )

    fun checkAnimeFavorite(malId: String, callback: GetCallbackFavorite)

    fun saveAnimeFavorite(entry: AnimeFavoriteEntry)

    fun removeAnimeFavorite(malId: String)

    interface GetCallback {
        fun onLoaded(entry: List<AnimeFavoriteEntry>)
        fun onError(errorMessage: String?)
        fun onFinished()
    }

    interface GetCallbackFavorite {
        fun onLoaded(entry: List<AnimeFavoriteEntry>)
        fun onError(errorMessage: String?)
        fun onFinished()
    }
}
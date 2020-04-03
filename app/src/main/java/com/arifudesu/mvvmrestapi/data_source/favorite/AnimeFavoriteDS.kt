package com.arifudesu.mvvmrestapi.data_source.favorite

import com.arifudesu.mvvmrestapi.model.AnimeFavorite

interface AnimeFavoriteDS {

    fun getAnimeFavorite(
        callback: GetCallback
    )

    fun saveAnimeFavorite(entry: AnimeFavorite)

    fun clearAnimeFavorite(malId: String)

    fun isThisAnimeFavorite(malId: String)

    interface GetCallback {
        fun onLoaded(entry: List<AnimeFavorite>)
        fun onError(errorMessage: String?)
        fun onFinished()
    }
}
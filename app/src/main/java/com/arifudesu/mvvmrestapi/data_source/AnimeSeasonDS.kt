package com.arifudesu.mvvmrestapi.data_source

import com.arifudesu.mvvmrestapi.model.AnimeEntry

interface AnimeSeasonDS {

    fun getAnimeSeason(
        seasonYear: String,
        seasonName: String,
        isRefresh: Boolean = false,
        callback: GetCallback
    )

    fun saveAnimeSeason(entry: List<AnimeEntry>)

    interface GetCallback {
        fun onLoaded(entry: List<AnimeEntry>)
        fun onError(errorMessage: String?)
        fun onFinished()
    }
}
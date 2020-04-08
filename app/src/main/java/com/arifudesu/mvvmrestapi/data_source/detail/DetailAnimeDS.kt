package com.arifudesu.mvvmrestapi.data_source.detail

import com.arifudesu.mvvmrestapi.model.detail.DetailAnimeEntry

interface DetailAnimeDS {

    fun getDetailAnime(
        malId: String,
        callback: GetCallback
    )

    fun saveDetailAnime(entry: DetailAnimeEntry)

    interface GetCallback {
        fun onLoaded(entry: DetailAnimeEntry)
        fun onError(errorMessage: String?)
        fun onFinished()
    }
}
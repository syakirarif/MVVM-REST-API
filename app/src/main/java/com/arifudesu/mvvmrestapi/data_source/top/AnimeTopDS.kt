package com.arifudesu.mvvmrestapi.data_source.top

import com.arifudesu.mvvmrestapi.model.AnimeTopEntry
import com.arifudesu.mvvmrestapi.model.AnimeTopUpcomingEntry

interface AnimeTopDS {

    fun getAnimeTop(
        isRefresh: Boolean = false,
        type: String,
        page: String,
        subtype: String,
        callback: GetCallback
    )

    fun getAnimeTopUpcoming(
        isRefresh: Boolean = false,
        type: String,
        page: String,
        subtype: String,
        callback: GetUpcomingCallback
    )

    fun saveAnimeTop(entry: List<AnimeTopEntry>)

    fun saveAnimeTopUpcoming(entry: List<AnimeTopUpcomingEntry>)

    interface GetCallback {
        fun onLoaded(entry: List<AnimeTopEntry>)
        fun onError(errorMessage: String?)
        fun onFinished()
    }

    interface GetUpcomingCallback {
        fun onLoaded(entry: List<AnimeTopUpcomingEntry>)
        fun onError(errorMessage: String?)
        fun onFinished()
    }
}
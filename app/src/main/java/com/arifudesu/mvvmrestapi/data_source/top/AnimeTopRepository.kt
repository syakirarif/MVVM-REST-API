package com.arifudesu.mvvmrestapi.data_source.top

import android.util.Log
import com.arifudesu.mvvmrestapi.model.AnimeTopEntry
import com.arifudesu.mvvmrestapi.model.AnimeTopUpcomingEntry

class AnimeTopRepository(
    val remoteDataSource: AnimeTopDS,
    val localDataSource: AnimeTopDS
) : AnimeTopDS {
    override fun getAnimeTop(
        isRefresh: Boolean,
        type: String,
        page: String,
        subtype: String,
        callback: AnimeTopDS.GetCallback
    ) {
        if (!isRefresh) {
            localDataSource.getAnimeTop(
                type = type,
                page = page,
                subtype = subtype,
                callback = callback
            )
            Log.e("getAnimeTop", "isRefresh: ${isRefresh}, getLocalDataSource()")
        } else {
            getRemoteDataSource(isRefresh, type, page, subtype, callback)
            Log.e("getAnimeTop", "isRefresh: ${isRefresh}, getRemoteDataSource()")
        }
    }

    override fun getAnimeTopUpcoming(
        isRefresh: Boolean,
        type: String,
        page: String,
        subtype: String,
        callback: AnimeTopDS.GetUpcomingCallback
    ) {
        if (!isRefresh) {
            localDataSource.getAnimeTopUpcoming(
                type = type,
                page = page,
                subtype = subtype,
                callback = callback
            )
            Log.e("getAnimeTopUpcoming", "isRefresh: ${isRefresh}, getLocalDataSource()")
        } else {
            getUpcomingRemoteDataSource(isRefresh, type, page, subtype, callback)
            Log.e("getAnimeTopUpcoming", "isRefresh: ${isRefresh}, getRemoteDataSource()")
        }
    }

    override fun saveAnimeTop(entry: List<AnimeTopEntry>) {
        localDataSource.saveAnimeTop(entry)
    }

    override fun saveAnimeTopUpcoming(entry: List<AnimeTopUpcomingEntry>) {
        localDataSource.saveAnimeTopUpcoming(entry)
    }

    private fun getRemoteDataSource(
        isRefresh: Boolean,
        type: String,
        page: String,
        subtype: String,
        callback: AnimeTopDS.GetCallback
    ) {
        remoteDataSource.getAnimeTop(
            isRefresh,
            type,
            page,
            subtype,
            object : AnimeTopDS.GetCallback {

                override fun onLoaded(entry: List<AnimeTopEntry>) {
                    saveAnimeTop(entry)
                    localDataSource.getAnimeTop(false, type, page, subtype, callback)
                }

                override fun onError(errorMessage: String?) {
                    callback.onError(errorMessage)
                }

                override fun onFinished() {
                    callback.onFinished()
                }

            }
        )
    }

    private fun getUpcomingRemoteDataSource(
        isRefresh: Boolean,
        type: String,
        page: String,
        subtype: String,
        callback: AnimeTopDS.GetUpcomingCallback
    ) {
        remoteDataSource.getAnimeTopUpcoming(
            isRefresh,
            type, page, subtype,
            object : AnimeTopDS.GetUpcomingCallback {
                override fun onLoaded(entry: List<AnimeTopUpcomingEntry>) {
                    saveAnimeTopUpcoming(entry)
                    localDataSource.getAnimeTopUpcoming(false, type, page, subtype, callback)
                }

                override fun onError(errorMessage: String?) {
                    callback.onError(errorMessage)
                }

                override fun onFinished() {
                    callback.onFinished()
                }

            }
        )
    }

    companion object {

        private var INSTANCE: AnimeTopRepository? = null

        @JvmStatic
        fun getInstance(remoteDataSource: AnimeTopDS, localDataSource: AnimeTopDS) =
            INSTANCE ?: synchronized(AnimeTopRepository::class.java) {
                INSTANCE ?: AnimeTopRepository(remoteDataSource, localDataSource)
                    .also { INSTANCE = it }
            }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
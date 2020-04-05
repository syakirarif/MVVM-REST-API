package com.arifudesu.mvvmrestapi.data_source

import android.util.Log
import com.arifudesu.mvvmrestapi.model.AnimeEntry

class AnimeSeasonRepository(
    val remoteDataSource: AnimeSeasonDS,
    val localDataSource: AnimeSeasonDS
) : AnimeSeasonDS {
    override fun getAnimeSeason(
        seasonYear: String,
        seasonName: String,
        isRefresh: Boolean,
        callback: AnimeSeasonDS.GetCallback
    ) {

        val kosong: Boolean = localDataSource.getAnimeSeason(
            seasonYear = seasonYear,
            seasonName = seasonName,
            callback = callback
        ).equals(null)

        if (isRefresh) {
            getRemoteDataSource(seasonYear, seasonName, isRefresh, callback)
            Log.e("AnimeSeasonRepository", "isRefresh: ${isRefresh}, getRemoteDataSource()")
        } else {
            localDataSource.getAnimeSeason(
                seasonYear = seasonYear,
                seasonName = seasonName,
                callback = callback
            )
            Log.e("AnimeSeasonRepository", "isRefresh: ${isRefresh}, getLocalDataSource()")
        }
    }

    override fun saveAnimeSeason(entry: List<AnimeEntry>) {
        localDataSource.saveAnimeSeason(entry)
    }

    private fun getRemoteDataSource(
        seasonYear: String,
        seasonName: String,
        isRefresh: Boolean,
        callback: AnimeSeasonDS.GetCallback
    ) {
        remoteDataSource.getAnimeSeason(
            seasonYear,
            seasonName,
            isRefresh,
            object : AnimeSeasonDS.GetCallback {
                override fun onLoaded(entry: List<AnimeEntry>) {
                    saveAnimeSeason(entry)
                    localDataSource.getAnimeSeason(seasonYear, seasonName, false, callback)
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

        private var INSTANCE: AnimeSeasonRepository? = null

        @JvmStatic
        fun getInstance(remoteDataSource: AnimeSeasonDS, localDataSource: AnimeSeasonDS) =
            INSTANCE ?: synchronized(AnimeSeasonRepository::class.java) {
                INSTANCE ?: AnimeSeasonRepository(remoteDataSource, localDataSource)
                    .also { INSTANCE = it }
            }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
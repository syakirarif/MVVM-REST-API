package com.arifudesu.mvvmrestapi.data_source.detail

import com.arifudesu.mvvmrestapi.data_source.favorite.AnimeFavoriteDS
import com.arifudesu.mvvmrestapi.model.AnimeFavoriteEntry
import com.arifudesu.mvvmrestapi.model.detail.DetailAnimeEntry

class DetailAnimeRepository(
    val remoteDataSource: DetailAnimeDS,
    val localDataSource: DetailAnimeDS,
    val localFavoriteDataSource: AnimeFavoriteDS
) : DetailAnimeDS, AnimeFavoriteDS {
    override fun getDetailAnime(
        malId: String,
        callback: DetailAnimeDS.GetCallback
    ) {
//        localDataSource.getDetailAnime(malId, callback)
        getRemoteDataSource(malId, callback)
    }

    override fun saveDetailAnime(entry: DetailAnimeEntry) {
        localDataSource.saveDetailAnime(entry)
    }

    private fun getRemoteDataSource(
        malId: String,
        callback: DetailAnimeDS.GetCallback
    ) {
        remoteDataSource.getDetailAnime(
            malId,
            object : DetailAnimeDS.GetCallback {
                override fun onLoaded(entry: DetailAnimeEntry) {
                    saveDetailAnime(entry)
                    localDataSource.getDetailAnime(malId, callback)
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

    override fun getAnimeFavorite(callback: AnimeFavoriteDS.GetCallback) {
        localFavoriteDataSource.getAnimeFavorite(callback)
    }

    override fun checkAnimeFavorite(malId: String, callback: AnimeFavoriteDS.GetCallbackFavorite) {
        localFavoriteDataSource.checkAnimeFavorite(malId, callback)
    }

    override fun saveAnimeFavorite(entry: AnimeFavoriteEntry) {
        localFavoriteDataSource.saveAnimeFavorite(entry)
    }

    override fun removeAnimeFavorite(malId: String) {
        localFavoriteDataSource.removeAnimeFavorite(malId)
    }

    companion object {

        private var INSTANCE: DetailAnimeRepository? = null

        @JvmStatic
        fun getInstance(remoteDataSource: DetailAnimeDS, localDataSource: DetailAnimeDS, localFavoriteDataSource: AnimeFavoriteDS) =
            INSTANCE ?: synchronized(DetailAnimeRepository::class.java) {
                INSTANCE ?: DetailAnimeRepository(remoteDataSource, localDataSource, localFavoriteDataSource)
                    .also { INSTANCE = it }
            }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
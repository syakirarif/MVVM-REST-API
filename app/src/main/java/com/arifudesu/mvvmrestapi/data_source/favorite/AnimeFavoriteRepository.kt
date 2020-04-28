package com.arifudesu.mvvmrestapi.data_source.favorite

import android.util.Log
import com.arifudesu.mvvmrestapi.model.AnimeFavoriteEntry

class AnimeFavoriteRepository(
    val localDataSource: AnimeFavoriteDS
) : AnimeFavoriteDS {

    override fun getAnimeFavorite(callback: AnimeFavoriteDS.GetCallback) {
        localDataSource.getAnimeFavorite(callback)
    }

    override fun checkAnimeFavorite(malId: String, callback: AnimeFavoriteDS.GetCallbackFavorite) {
        localDataSource.checkAnimeFavorite(malId, callback)
    }

    override fun saveAnimeFavorite(entry: AnimeFavoriteEntry) {
        localDataSource.saveAnimeFavorite(entry)
        Log.e("AnimeFavoriteRepository", "saveAnimeFavorite => ${entry}")
    }

    override fun removeAnimeFavorite(malId: String) {
        localDataSource.removeAnimeFavorite(malId)
        Log.e("AnimeFavoriteRepository", "removeAnimeFavorite => ${malId}")
    }

    companion object {

        private var INSTANCE: AnimeFavoriteRepository? = null

        @JvmStatic
        fun getInstance(localDataSource: AnimeFavoriteDS) =
            INSTANCE
                ?: synchronized(AnimeFavoriteRepository::class.java) {
                    INSTANCE
                        ?: AnimeFavoriteRepository(
                            localDataSource
                        )
                            .also { INSTANCE = it }
                }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
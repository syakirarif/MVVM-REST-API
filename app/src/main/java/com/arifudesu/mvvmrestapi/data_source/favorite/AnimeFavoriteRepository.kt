package com.arifudesu.mvvmrestapi.data_source.favorite

import com.arifudesu.mvvmrestapi.model.AnimeFavorite

class AnimeFavoriteRepository(
    val localDataSource: AnimeFavoriteDS
) : AnimeFavoriteDS {
    override fun getAnimeFavorite(
        callback: AnimeFavoriteDS.GetCallback
    ) {
        localDataSource.getAnimeFavorite(
            callback = callback
        )
    }

    override fun saveAnimeFavorite(entry: AnimeFavorite) {
        localDataSource.saveAnimeFavorite(entry)
    }

    override fun clearAnimeFavorite(malId: String) {
        localDataSource.clearAnimeFavorite(malId)
    }

    override fun isThisAnimeFavorite(malId: String) {
        localDataSource.isThisAnimeFavorite(malId)
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
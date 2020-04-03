package com.arifudesu.mvvmrestapi.data_source.favorite

import androidx.annotation.VisibleForTesting
import com.arifudesu.mvvmrestapi.model.AnimeFavorite
import com.arifudesu.mvvmrestapi.room.AnimeDao
import com.arifudesu.mvvmrestapi.util.dbhelper.AppExecutors

class AnimeFavoriteLDS private constructor(
    val appExecutors: AppExecutors,
    val dataDao: AnimeDao
) : AnimeFavoriteDS {
    override fun getAnimeFavorite(
        callback: AnimeFavoriteDS.GetCallback
    ) {
        appExecutors.diskIO.execute {
            val getDao = dataDao.getFavorite()

            appExecutors.mainThread.execute {
                if (getDao.equals(null)) {
                    callback.onError("Data kosong")
                } else {
                    callback.onLoaded(getDao)
                }
            }
        }
    }

    override fun clearAnimeFavorite(malId: String) {
        dataDao.clearFavorite(malId)
    }

    override fun isThisAnimeFavorite(malId: String) {
        dataDao.isAnimeFavorite(malId)
    }

    override fun saveAnimeFavorite(entry: AnimeFavorite) {
//        dataDao.clearAllFavorite()

//        for (model in entry) {
//
//        }
        dataDao.insertFavorite(entry)

    }

    companion object {
        private var INSTANCE: AnimeFavoriteLDS? = null

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors, dataDao: AnimeDao): AnimeFavoriteLDS {
            if (INSTANCE == null) {
                synchronized(AnimeFavoriteLDS::javaClass) {
                    INSTANCE =
                        AnimeFavoriteLDS(
                            appExecutors,
                            dataDao
                        )
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }
}
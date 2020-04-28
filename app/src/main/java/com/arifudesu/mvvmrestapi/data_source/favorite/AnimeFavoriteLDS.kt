package com.arifudesu.mvvmrestapi.data_source.favorite

import android.util.Log
import androidx.annotation.VisibleForTesting
import com.arifudesu.mvvmrestapi.model.AnimeFavoriteEntry
import com.arifudesu.mvvmrestapi.room.AnimeDao
import com.arifudesu.mvvmrestapi.util.dbhelper.AppExecutors

class AnimeFavoriteLDS private constructor(
    val appExecutors: AppExecutors,
    val dataDao: AnimeDao
) : AnimeFavoriteDS {

    override fun getAnimeFavorite(callback: AnimeFavoriteDS.GetCallback) {
        appExecutors.diskIO.execute {
            val getDao = dataDao.getAnimeFavorite()

            appExecutors.mainThread.execute {
                if (getDao.isNullOrEmpty()) {
                    callback.onError("Data kosong")
                } else {
                    callback.onLoaded(getDao)
                }
            }
        }
    }

    override fun checkAnimeFavorite(malId: String, callback: AnimeFavoriteDS.GetCallbackFavorite) {
        appExecutors.diskIO.execute {
            val getDao = dataDao.checkAnimeFavorite(malId)

            appExecutors.mainThread.execute {
                if (getDao.isNullOrEmpty()) {
                    callback.onError("Data kosong")
                } else {
                    callback.onLoaded(getDao)
                }
            }
        }
    }

    override fun saveAnimeFavorite(entry: AnimeFavoriteEntry) {
        dataDao.insertAnimeFavorite(entry)
        Log.e("AnimeFavoriteLDS", "insertAnimeFavorite => ${entry}")
    }

    override fun removeAnimeFavorite(malId: String) {
        dataDao.removeAnimeFavorite(malId)
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
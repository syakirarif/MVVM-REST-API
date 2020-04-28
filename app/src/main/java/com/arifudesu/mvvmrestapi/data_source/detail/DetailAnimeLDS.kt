package com.arifudesu.mvvmrestapi.data_source.detail

import androidx.annotation.VisibleForTesting
import com.arifudesu.mvvmrestapi.data_source.favorite.AnimeFavoriteDS
import com.arifudesu.mvvmrestapi.model.AnimeFavoriteEntry
import com.arifudesu.mvvmrestapi.model.detail.DetailAnimeEntry
import com.arifudesu.mvvmrestapi.room.AnimeDao
import com.arifudesu.mvvmrestapi.util.dbhelper.AppExecutors

class DetailAnimeLDS private constructor(
    val appExecutors: AppExecutors,
    val dataDao: AnimeDao
) : DetailAnimeDS, AnimeFavoriteDS {
    override fun getDetailAnime(
        malId: String,
        callback: DetailAnimeDS.GetCallback
    ) {
        appExecutors.diskIO.execute {
            val getDao = dataDao.getDetailAnime()

            appExecutors.mainThread.execute {
                if (getDao.equals(null)) {
                    callback.onError("Data kosong")
                } else {
                    callback.onLoaded(getDao)
                }
            }
        }
    }

    override fun saveDetailAnime(entry: DetailAnimeEntry) {
        dataDao.clearTableDetailAnime()

        dataDao.insertDetailAnime(entry)

    }

    companion object {
        private var INSTANCE: DetailAnimeLDS? = null

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors, dataDao: AnimeDao): DetailAnimeLDS {
            if (INSTANCE == null) {
                synchronized(DetailAnimeLDS::javaClass) {
                    INSTANCE = DetailAnimeLDS(appExecutors, dataDao)
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }

    override fun getAnimeFavorite(callback: AnimeFavoriteDS.GetCallback) {
        appExecutors.diskIO.execute {
            val getDao = dataDao.getAnimeFavorite()

            appExecutors.mainThread.execute {
                if (getDao.equals(null)) {
                    callback.onError("Tidak Ada Favorite")
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
                if (getDao.equals(null)) {
                    callback.onError("Data kosong")
                } else {
                    callback.onLoaded(getDao)
                }
            }
        }
    }

    override fun saveAnimeFavorite(entry: AnimeFavoriteEntry) {
        dataDao.removeAnimeFavorite(entry.malId.toString())
        dataDao.insertAnimeFavorite(entry)
    }

    override fun removeAnimeFavorite(malId: String) {
        dataDao.removeAnimeFavorite(malId)
    }
}
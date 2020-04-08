package com.arifudesu.mvvmrestapi.data_source.detail

import androidx.annotation.VisibleForTesting
import com.arifudesu.mvvmrestapi.model.detail.DetailAnimeEntry
import com.arifudesu.mvvmrestapi.room.AnimeDao
import com.arifudesu.mvvmrestapi.util.dbhelper.AppExecutors

class DetailAnimeLDS private constructor(
    val appExecutors: AppExecutors,
    val dataDao: AnimeDao
) : DetailAnimeDS {
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
}
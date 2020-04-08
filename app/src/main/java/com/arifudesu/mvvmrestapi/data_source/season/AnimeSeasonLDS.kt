package com.arifudesu.mvvmrestapi.data_source.season

import androidx.annotation.VisibleForTesting
import com.arifudesu.mvvmrestapi.model.AnimeEntry
import com.arifudesu.mvvmrestapi.room.AnimeDao
import com.arifudesu.mvvmrestapi.util.dbhelper.AppExecutors

class AnimeSeasonLDS private constructor(
    val appExecutors: AppExecutors,
    val dataDao: AnimeDao
) : AnimeSeasonDS {
    override fun getAnimeSeason(
        seasonYear: String,
        seasonName: String,
        isRefresh: Boolean,
        callback: AnimeSeasonDS.GetCallback
    ) {
        appExecutors.diskIO.execute {
            val getDao = dataDao.getEntry()

            appExecutors.mainThread.execute {
                if (getDao.equals(null)) {
                    callback.onError("Data kosong")
                } else {
                    callback.onLoaded(getDao)
                }
            }
        }
    }

    override fun saveAnimeSeason(entry: List<AnimeEntry>) {
        dataDao.clearTable()

        for (model in entry) {
            dataDao.insertEntry(model)
        }

    }

//    override fun insertAnimeFavorite(entry: AnimeEntry) {
//
//        val data = AnimeFavoriteEntry(
//            entry.id,
//            entry.malId,
//            entry.title,
//            entry.url,
//            entry.imageUrl,
//            entry.type
//        )
//
//        dataDao.insertAnimeFavorite(data)
//        Log.e("AnimeSeasonLDS", "insertAnimeFavorite => ${data}")
//    }
//
//    override fun checkAnimeFavorite(malId: String, callback: AnimeSeasonDS.GetFavoriteCallback) {
//        appExecutors.diskIO.execute {
//            val getDao = dataDao.checkAnimeFavorite(malId)
//
//            appExecutors.mainThread.execute {
//                if (getDao.equals(null)) {
//                    callback.onError("Data kosong")
//                } else {
//                    callback.onLoaded(getDao)
//                }
//
//            }
//        }
//    }

    companion object {
        private var INSTANCE: AnimeSeasonLDS? = null

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors, dataDao: AnimeDao): AnimeSeasonLDS {
            if (INSTANCE == null) {
                synchronized(AnimeSeasonLDS::javaClass) {
                    INSTANCE =
                        AnimeSeasonLDS(
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
package com.arifudesu.mvvmrestapi.data_source.top

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import com.arifudesu.mvvmrestapi.model.AnimeTopEntry
import com.arifudesu.mvvmrestapi.model.AnimeTopUpcomingEntry
import com.arifudesu.mvvmrestapi.room.AnimeDao
import com.arifudesu.mvvmrestapi.util.dbhelper.AppExecutors

class AnimeTopLDS private constructor(
    val appExecutors: AppExecutors,
    val dataDao: AnimeDao
) : AnimeTopDS {
    override fun getAnimeTop(
        isRefresh: Boolean,
        type: String,
        page: String,
        subtype: String,
        callback: AnimeTopDS.GetCallback
    ) {
        appExecutors.diskIO.execute {
            val getDao = dataDao.getAnimeTop()

            appExecutors.mainThread.execute {
                if (getDao.equals(null)) {
                    callback.onError("Data kosong")
                } else {
                    callback.onLoaded(getDao)
                }
            }
        }
    }

    override fun getAnimeTopUpcoming(
        isRefresh: Boolean,
        type: String,
        page: String,
        subtype: String,
        callback: AnimeTopDS.GetUpcomingCallback
    ) {
        appExecutors.diskIO.execute {
            val getDao = dataDao.getAnimeTopUpcoming()

            appExecutors.mainThread.execute {
                if (getDao.equals(null)) {
                    callback.onError("Data kosong")
                } else {
                    callback.onLoaded(getDao)
                }
            }
        }
    }

    override fun saveAnimeTop(entry: List<AnimeTopEntry>) {
        dataDao.clearTableAnimeTop()

        for (model in entry)
            dataDao.insertAnimeTop(model)

    }

    override fun saveAnimeTopUpcoming(entry: List<AnimeTopUpcomingEntry>) {
        dataDao.clearTableAnimeTopUpcoming()

        for (model in entry)
            dataDao.insertAnimeTopUpcoming(model)
    }

    companion object {
        private var INSTANCE: AnimeTopLDS? = null

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors, dataDao: AnimeDao): AnimeTopLDS {
            if (INSTANCE == null) {
                synchronized(AnimeTopLDS::javaClass) {
                    INSTANCE = AnimeTopLDS(appExecutors, dataDao)
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
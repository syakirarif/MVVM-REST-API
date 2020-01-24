package com.arifudesu.mvvmrestapi.util

import android.content.Context
import com.arifudesu.mvvmrestapi.data_source.AnimeSeasonLDS
import com.arifudesu.mvvmrestapi.data_source.AnimeSeasonRDS
import com.arifudesu.mvvmrestapi.data_source.AnimeSeasonRepository
import com.arifudesu.mvvmrestapi.room.AnimeDatabase
import com.arifudesu.mvvmrestapi.util.dbhelper.AppExecutors

object Injection {

    fun provideAnimeSeasonRepository(context: Context): AnimeSeasonRepository {
        val localDatabase = AnimeDatabase.invoke(context)

        return AnimeSeasonRepository.getInstance(
            AnimeSeasonRDS,
            AnimeSeasonLDS.getInstance(AppExecutors(), localDatabase.animeDao())
        )
    }
}
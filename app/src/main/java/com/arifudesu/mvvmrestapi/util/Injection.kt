package com.arifudesu.mvvmrestapi.util

import android.content.Context
import com.arifudesu.mvvmrestapi.data_source.favorite.AnimeFavoriteLDS
import com.arifudesu.mvvmrestapi.data_source.favorite.AnimeFavoriteRepository
import com.arifudesu.mvvmrestapi.data_source.season.AnimeSeasonLDS
import com.arifudesu.mvvmrestapi.data_source.season.AnimeSeasonRDS
import com.arifudesu.mvvmrestapi.data_source.season.AnimeSeasonRepository
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

    fun provideAnimeFavoriteRepository(context: Context): AnimeFavoriteRepository {
        val localDatabase = AnimeDatabase.invoke(context)

        return AnimeFavoriteRepository.getInstance(
            AnimeFavoriteLDS.getInstance(AppExecutors(), localDatabase.animeDao())
        )
    }



}
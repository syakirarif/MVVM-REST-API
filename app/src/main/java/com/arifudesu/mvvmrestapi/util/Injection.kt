package com.arifudesu.mvvmrestapi.util

import android.content.Context
import com.arifudesu.mvvmrestapi.data_source.detail.DetailAnimeLDS
import com.arifudesu.mvvmrestapi.data_source.detail.DetailAnimeRDS
import com.arifudesu.mvvmrestapi.data_source.detail.DetailAnimeRepository
import com.arifudesu.mvvmrestapi.data_source.favorite.AnimeFavoriteLDS
import com.arifudesu.mvvmrestapi.data_source.favorite.AnimeFavoriteRepository
import com.arifudesu.mvvmrestapi.data_source.season.AnimeSeasonLDS
import com.arifudesu.mvvmrestapi.data_source.season.AnimeSeasonRDS
import com.arifudesu.mvvmrestapi.data_source.season.AnimeSeasonRepository
import com.arifudesu.mvvmrestapi.data_source.top.AnimeTopLDS
import com.arifudesu.mvvmrestapi.data_source.top.AnimeTopRDS
import com.arifudesu.mvvmrestapi.data_source.top.AnimeTopRepository
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

    fun provideAnimeTopRepository(context: Context): AnimeTopRepository {
        val localDatabase = AnimeDatabase.invoke(context)

        return AnimeTopRepository.getInstance(
            AnimeTopRDS,
            AnimeTopLDS.getInstance(AppExecutors(), localDatabase.animeDao())
        )
    }

    fun provideAnimeFavoriteRepository(context: Context): AnimeFavoriteRepository {
        val localDatabase = AnimeDatabase.invoke(context)

        return AnimeFavoriteRepository.getInstance(
            AnimeFavoriteLDS.getInstance(AppExecutors(), localDatabase.animeDao())
        )
    }

    fun provideDetailAnimeRepository(context: Context): DetailAnimeRepository {
        val localDatabase = AnimeDatabase.invoke(context)

        return DetailAnimeRepository.getInstance(
            DetailAnimeRDS,
            DetailAnimeLDS.getInstance(AppExecutors(), localDatabase.animeDao())
        )
    }
}
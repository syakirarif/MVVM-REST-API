package com.arifudesu.mvvmrestapi.data_source

import android.annotation.SuppressLint
import com.arifudesu.mvvmrestapi.model.AnimeEntry
import com.arifudesu.mvvmrestapi.service.AnimeApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object AnimeSeasonRDS : AnimeSeasonDS {

    private val apiService: AnimeApiService by lazy {
        AnimeApiService.getAnimeApiService
    }

    @SuppressLint("CheckResult")
    override fun getAnimeSeason(
        seasonYear: String,
        seasonName: String,
        isRefresh: Boolean,
        callback: AnimeSeasonDS.GetCallback
    ) {
        apiService.getAnimeSeason(seasonYear, seasonName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ results ->
                run {
                    if (results.anime!!.equals(null)) {
                        callback.onFinished()
                    } else {
                        callback.onLoaded(results.anime!!)
                    }
                }
            }, { error ->
                callback.onError(error.message)
            })
    }

    override fun saveAnimeSeason(entry: List<AnimeEntry>) {

    }
}
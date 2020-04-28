package com.arifudesu.mvvmrestapi.data_source.detail

import android.annotation.SuppressLint
import com.arifudesu.mvvmrestapi.model.detail.DetailAnimeEntry
import com.arifudesu.mvvmrestapi.service.AnimeApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object DetailAnimeRDS : DetailAnimeDS {

    private val apiService: AnimeApiService by lazy {
        AnimeApiService.getAnimeApiService
    }

    @SuppressLint("CheckResult")
    override fun getDetailAnime(
        malId: String,
        callback: DetailAnimeDS.GetCallback
    ) {
        apiService.getDetailAnime(malId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ results ->
                run {
                    if (results.equals(null)) {
                        callback.onFinished()
                    } else {
                        callback.onLoaded(results)
                    }
                }
            }, { error ->
                callback.onError(error.message)
            })
    }

    override fun saveDetailAnime(entry: DetailAnimeEntry) {

    }

}
package com.arifudesu.mvvmrestapi.data_source.top

import android.annotation.SuppressLint
import com.arifudesu.mvvmrestapi.model.AnimeTopEntry
import com.arifudesu.mvvmrestapi.model.AnimeTopUpcomingEntry
import com.arifudesu.mvvmrestapi.service.AnimeApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object AnimeTopRDS : AnimeTopDS {

    private val apiService: AnimeApiService by lazy {
        AnimeApiService.getAnimeApiService
    }

    @SuppressLint("CheckResult")
    override fun getAnimeTop(
        isRefresh: Boolean,
        type: String,
        page: String,
        subtype: String,
        callback: AnimeTopDS.GetCallback
    ) {
        apiService.getAnimeTop(type, page, subtype)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ results ->
                run {
                    if (results.top!!.equals(null)) {
                        callback.onFinished()
                    } else {
                        callback.onLoaded(results.top!!)
                    }
                }
            }, { error ->
                callback.onError(error.message)
            })
    }

    @SuppressLint("CheckResult")
    override fun getAnimeTopUpcoming(
        isRefresh: Boolean,
        type: String,
        page: String,
        subtype: String,
        callback: AnimeTopDS.GetUpcomingCallback
    ) {
        apiService.getAnimeTop(type, page, subtype)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ results ->
                run {
                    if (results.top!!.equals(null)) {
                        callback.onFinished()
                    } else {

                        val result = results.top!!

                        val content = ArrayList<AnimeTopUpcomingEntry>()

                        for (i in result.indices)
                            content.add(
                                AnimeTopUpcomingEntry(
                                    result[i].id,
                                    result[i].malId,
                                    result[i].rank,
                                    result[i].title,
                                    result[i].url,
                                    result[i].imageUrl,
                                    result[i].members,
                                    result[i].score
                                )
                            )


                        callback.onLoaded(content)
                    }
                }
            }, { error ->
                callback.onError(error.message)
            })
    }

    override fun saveAnimeTop(entry: List<AnimeTopEntry>) {

    }

    override fun saveAnimeTopUpcoming(entry: List<AnimeTopUpcomingEntry>) {

    }
}
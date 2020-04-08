package com.arifudesu.mvvmrestapi.view.dashboard.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.arifudesu.mvvmrestapi.R
import com.arifudesu.mvvmrestapi.data_source.season.AnimeSeasonDS
import com.arifudesu.mvvmrestapi.data_source.season.AnimeSeasonRepository
import com.arifudesu.mvvmrestapi.model.AnimeEntry
import com.arifudesu.mvvmrestapi.util.SingleLiveEvent
import com.orhanobut.hawk.Hawk


class MainVM(
    context: Application,
    private val repository: AnimeSeasonRepository
) : AndroidViewModel(context) {

    val dataListLive = MutableLiveData<List<AnimeEntry>>()
    val openData = SingleLiveEvent<AnimeEntry>()
//    val openFavorite = SingleLiveEvent<AnimeEntry>()

    var showProgress = MutableLiveData<Boolean>()

//    var isFavorite = MutableLiveData<Boolean>()

//    private lateinit var isFavorite: Boolean

//    val isLoading = ObservableField(true)

    var year: String? = context.getString(R.string.CURRENT_YEAR)
    var name: String? = context.getString(R.string.CURRENT_SEASON)

    fun start(seasonYear: String, seasonName: String, isRefresh: Boolean) {
        showProgress.value = true
//        isFavorite.value = false
        year = seasonYear
        name = seasonName
        getData(seasonYear, seasonName, isRefresh)
    }

    fun onRefresh(seasonYear: String, seasonName: String) {
        showProgress.value = true
        dataListLive.postValue(null)
        getData(seasonYear, seasonName, true)
    }

    private fun getData(seasonYear: String, seasonName: String, isRefresh: Boolean = false) {
//        showProgress.value = true

        repository.getAnimeSeason(
            seasonYear,
            seasonName,
            isRefresh,
            object : AnimeSeasonDS.GetCallback {
                override fun onLoaded(entry: List<AnimeEntry>) {
                    dataListLive.postValue(entry)
                    showProgress.value = false
                    Hawk.put("IS_MAIN_NEED_REFRESH", false)
                }

                override fun onError(errorMessage: String?) {
                    showProgress.value = false
                }

                override fun onFinished() {
                    showProgress.value = false
                }

            }
        )
    }

//    fun insertAnimeFavorite(entry: AnimeEntry) {
//        repository.insertAnimeFavorite(entry)
//    }

//    fun checkAnimeFavorite(malId: String): Boolean {
//        var isFav: Boolean = false
//        isFavorite.value = false
//
//        repository.checkAnimeFavorite(malId, object : AnimeSeasonDS.GetFavoriteCallback {
//            override fun onLoaded(count: Int) {
//                isFavorite.value = true
//                Log.e("MainVM", "checkAnimeFavorite(${malId}) => ${count}")
//                isFav = count > 0
//            }
//
//            override fun onError(errorMessage: String?) {
//                isFavorite.value = false
//                isFav = false
//            }
//
//            override fun onFinished() {
//                isFavorite.value = false
//                isFav = false
//            }
//
//        })
//
//        return isFav
//    }

}
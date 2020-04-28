package com.arifudesu.mvvmrestapi.view.detail

import android.app.Application
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.arifudesu.mvvmrestapi.data_source.detail.DetailAnimeDS
import com.arifudesu.mvvmrestapi.data_source.detail.DetailAnimeRepository
import com.arifudesu.mvvmrestapi.data_source.favorite.AnimeFavoriteDS
import com.arifudesu.mvvmrestapi.model.AnimeFavoriteEntry
import com.arifudesu.mvvmrestapi.model.detail.DetailAnimeEntry


class DetailAnimeVM(
    context: Application,
    private val repository: DetailAnimeRepository
) : AndroidViewModel(context) {

    val entries: MutableLiveData<DetailAnimeEntry> = MutableLiveData()
    var showProgress = MutableLiveData<Boolean>()
    var isFavorite = MutableLiveData<Boolean>()

    fun getDetailAnime(malId: String) {
        showProgress.value = true

        repository.getDetailAnime(malId, object : DetailAnimeDS.GetCallback {
            override fun onLoaded(entry: DetailAnimeEntry) {
                entries.postValue(entry)
                showProgress.value = false
            }

            override fun onError(errorMessage: String?) {
                showProgress.value = false
            }

            override fun onFinished() {
                showProgress.value = false
            }

        })
    }

    fun checkAnimeFavorite(malId: String) {
        repository.checkAnimeFavorite(malId, object : AnimeFavoriteDS.GetCallbackFavorite {
            override fun onLoaded(entry: List<AnimeFavoriteEntry>) {
                isFavorite.value = true
            }

            override fun onError(errorMessage: String?) {
                Log.e("checkAnimeFavorite", errorMessage!!)
                isFavorite.value = false
            }

            override fun onFinished() {
                isFavorite.value = false
            }

        })
    }

    fun onClickFavorite(entry: DetailAnimeEntry){
        if(isFavorite.value == true){
            removeAnimeFromFavorite(entry.malId.toString())
        } else {

            val favEntry = AnimeFavoriteEntry(
                entry.id,
                entry.malId,
                entry.title,
                entry.url,
                entry.imageUrl,
                entry.type
            )
            insertAnimeToFavorite(favEntry)
        }
    }

    fun insertAnimeToFavorite(entry: AnimeFavoriteEntry){
        repository.saveAnimeFavorite(entry)
        isFavorite.value = true
        Log.e("insertAnimeToFavorite", "${isFavorite.value}")
    }

    fun removeAnimeFromFavorite(malId: String){
        repository.removeAnimeFavorite(malId)
        isFavorite.value = false
        Log.e("removeAnimeFromFavorite", "${isFavorite.value}")
    }

}
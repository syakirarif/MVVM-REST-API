package com.arifudesu.mvvmrestapi.view.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arifudesu.mvvmrestapi.data_source.favorite.AnimeFavoriteDS
import com.arifudesu.mvvmrestapi.data_source.favorite.AnimeFavoriteRepository
import com.arifudesu.mvvmrestapi.model.AnimeFavorite

class FavoriteVM(private val repository: AnimeFavoriteRepository) : ViewModel() {

    val favoriteLiveData = MutableLiveData<List<AnimeFavorite>>()
    var showProgress = MutableLiveData<Boolean>()

    fun getAnimeFavorite(): LiveData<List<AnimeFavorite>> {
        repository.getAnimeFavorite(object : AnimeFavoriteDS.GetCallback {
            override fun onLoaded(entry: List<AnimeFavorite>) {
                favoriteLiveData.postValue(entry)
                showProgress.value = false
            }

            override fun onError(errorMessage: String?) {
                showProgress.value = false
            }

            override fun onFinished() {
                showProgress.value = false
            }

        })

        return favoriteLiveData
    }

    fun saveAnimeFavorite(entry: AnimeFavorite) {
        repository.saveAnimeFavorite(entry)
    }

    fun deleteAnimeFavorite(malId: String){
        repository.clearAnimeFavorite(malId)
    }

    fun isThisAnimeFavorite(malId: String){
        repository.isThisAnimeFavorite(malId)
    }
}
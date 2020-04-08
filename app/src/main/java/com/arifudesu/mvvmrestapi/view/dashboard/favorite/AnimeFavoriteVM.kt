package com.arifudesu.mvvmrestapi.view.dashboard.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.arifudesu.mvvmrestapi.data_source.favorite.AnimeFavoriteDS
import com.arifudesu.mvvmrestapi.data_source.favorite.AnimeFavoriteRepository
import com.arifudesu.mvvmrestapi.model.AnimeFavoriteEntry
import com.arifudesu.mvvmrestapi.util.SingleLiveEvent


class AnimeFavoriteVM(
    context: Application,
    private val repository: AnimeFavoriteRepository
) : AndroidViewModel(context) {

    val dataListLive: MutableLiveData<List<AnimeFavoriteEntry>> = MutableLiveData()
    val openData = SingleLiveEvent<AnimeFavoriteEntry>()
    var showProgress = MutableLiveData<Boolean>()

    fun start() {
        //getDosen(nama, true)
    }

    fun getAnimeFavorite() {
        showProgress.value = true

        repository.getAnimeFavorite(object : AnimeFavoriteDS.GetCallback {
            override fun onLoaded(entry: List<AnimeFavoriteEntry>) {
                dataListLive.postValue(entry)
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

    fun insertAnimeFavorite(entry: AnimeFavoriteEntry) {
        repository.saveAnimeFavorite(entry)
        getAnimeFavorite()
    }

    fun removeAnimeFavorite(malId: String){
        repository.removeAnimeFavorite(malId)
        getAnimeFavorite()
    }

//    private fun getDosen(nama: String, isRefresh: Boolean = false) {
//        showProgress.value = true
//
//        repository.getFakultasDosen(
//            isRefresh,
//            nama,
//            object : AnimeFavoriteDS.GetCallback {
//                override fun onLoaded(entry: List<AnimeFavoriteEntry>) {
//                    dataListLive.clear()
//                    dataListLive.addAll(entry.map {
//                        AnimeFavoriteEntry(
//                            it.id,
//                            it.niy,
//                            it.nama,
//                            it.jabatan,
//                            it.url_photo,
//                            it.email
//                        )
//                    })
//                    showProgress.value = false
//                }
//
//                override fun onError(errorMessage: String?) {
//                    showProgress.value = false
//                }
//
//                override fun onFinished() {
//                    showProgress.value = false
//                }
//
//            }
//        )
//    }

}
package com.arifudesu.mvvmrestapi.view.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.arifudesu.mvvmrestapi.data_source.detail.DetailAnimeDS
import com.arifudesu.mvvmrestapi.data_source.detail.DetailAnimeRepository
import com.arifudesu.mvvmrestapi.model.detail.DetailAnimeEntry


class DetailAnimeVM(
    context: Application,
    private val repository: DetailAnimeRepository
) : AndroidViewModel(context) {

    val entries: MutableLiveData<DetailAnimeEntry> = MutableLiveData()
    var showProgress = MutableLiveData<Boolean>()

    fun getDetailAnime(malId: String) {
        showProgress.value = true

        repository.getDetailAnime(malId, object : DetailAnimeDS.GetCallback{
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
//
//        repository.getDetailAnime(
//            isRefresh,
//            nama,
//            object : DetailAnimeDS.GetCallback {
//                override fun onLoaded(entry: DetailAnimeEntry) {
//                    entries.set(entry)
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
    }

}
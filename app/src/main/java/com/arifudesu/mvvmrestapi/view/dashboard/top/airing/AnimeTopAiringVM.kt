package com.arifudesu.mvvmrestapi.view.dashboard.top.airing

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.arifudesu.mvvmrestapi.data_source.top.AnimeTopDS
import com.arifudesu.mvvmrestapi.data_source.top.AnimeTopRepository
import com.arifudesu.mvvmrestapi.model.AnimeTopEntry
import com.arifudesu.mvvmrestapi.util.SingleLiveEvent
import com.orhanobut.hawk.Hawk


class AnimeTopAiringVM(
    context: Application,
    private val repository: AnimeTopRepository
) : AndroidViewModel(context) {

    //    val dataListLive: ObservableList<AnimeTopEntry> = ObservableArrayList()
    val dataListLive = MutableLiveData<List<AnimeTopEntry>>()
    val dataListLiveUpcoming = MutableLiveData<List<AnimeTopEntry>>()
    val openData = SingleLiveEvent<AnimeTopEntry>()
    var showProgress = MutableLiveData<Boolean>()
    var showProgressUpcoming = MutableLiveData<Boolean>()

    fun start(type: String, page: String, subtype: String, isRefresh: Boolean) {
//        dataListLive.clear()
        //getDosen(nama, true)
        showProgress.value = true
        getData(type, page, subtype, isRefresh)
    }

    fun onRefresh(type: String, page: String, subtype: String) {
        showProgress.value = true
        dataListLive.postValue(null)
        getData(type, page, subtype, true)
    }

    private fun getData(type: String, page: String, subtype: String, isRefresh: Boolean) {
        repository.getAnimeTop(
            isRefresh,
            type,
            page,
            subtype,
            object : AnimeTopDS.GetCallback {
                override fun onLoaded(entry: List<AnimeTopEntry>) {
                    dataListLive.postValue(entry)
                    showProgress.value = false
                    Hawk.put("IS_TOP_NEED_REFRESH", false)
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

//    private fun getDosen(nama: String, isRefresh: Boolean = false) {
//        showProgress.value = true
//
//        repository.getFakultasDosen(
//            isRefresh,
//            nama,
//            object : AnimeTopDS.GetCallback {
//                override fun onLoaded(entry: List<AnimeTopEntry>) {
//                    dataListLive.clear()
//                    dataListLive.addAll(entry.map {
//                        AnimeTopEntry(
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
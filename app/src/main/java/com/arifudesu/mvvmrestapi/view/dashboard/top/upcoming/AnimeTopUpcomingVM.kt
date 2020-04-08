package com.arifudesu.mvvmrestapi.view.dashboard.top.upcoming

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.arifudesu.mvvmrestapi.data_source.top.AnimeTopDS
import com.arifudesu.mvvmrestapi.data_source.top.AnimeTopRepository
import com.arifudesu.mvvmrestapi.model.AnimeTopUpcomingEntry
import com.arifudesu.mvvmrestapi.util.SingleLiveEvent
import com.orhanobut.hawk.Hawk


class AnimeTopUpcomingVM(
    context: Application,
    private val repository: AnimeTopRepository
) : AndroidViewModel(context) {

    //    val dataListLive: ObservableList<AnimeTopEntry> = ObservableArrayList()
    val dataListLive = MutableLiveData<List<AnimeTopUpcomingEntry>>()
    val openData = SingleLiveEvent<AnimeTopUpcomingEntry>()
    var showProgress = MutableLiveData<Boolean>()

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
        repository.getAnimeTopUpcoming(
            isRefresh,
            type,
            page,
            subtype,
            object : AnimeTopDS.GetUpcomingCallback {
                override fun onLoaded(entry: List<AnimeTopUpcomingEntry>) {
                    dataListLive.postValue(entry)
                    showProgress.value = false
                    Hawk.put("IS_TOP_UPCOMING_NEED_REFRESH", false)
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

}
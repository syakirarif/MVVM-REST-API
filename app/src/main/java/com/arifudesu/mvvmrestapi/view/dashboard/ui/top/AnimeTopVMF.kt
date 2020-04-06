package com.arifudesu.mvvmrestapi.view.dashboard.ui.top

import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arifudesu.mvvmrestapi.data_source.top.AnimeTopRepository
import com.arifudesu.mvvmrestapi.util.Injection
import com.arifudesu.mvvmrestapi.view.dashboard.ui.top.airing.AnimeTopAiringVM
import com.arifudesu.mvvmrestapi.view.dashboard.ui.top.upcoming.AnimeTopUpcomingVM

class AnimeTopVMF(
    private val application: Application,
    private val repository: AnimeTopRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(AnimeTopAiringVM::class.java) ->
                    AnimeTopAiringVM(
                        application,
                        repository
                    )
                isAssignableFrom(AnimeTopUpcomingVM::class.java) ->
                    AnimeTopUpcomingVM(
                        application,
                        repository
                    )
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: AnimeTop")
            } as T
        }

    companion object {
        @Volatile
        private var INSTANCE: AnimeTopVMF? = null

        fun getInstance(application: Application) =
            INSTANCE ?: AnimeTopVMF(
                application,
                Injection.provideAnimeTopRepository(application.applicationContext)

            ).also { INSTANCE = it }
    }

    @VisibleForTesting
    fun destroyInstance() {
        INSTANCE = null
    }
}

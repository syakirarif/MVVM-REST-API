package com.arifudesu.mvvmrestapi.view.detail

import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arifudesu.mvvmrestapi.data_source.detail.DetailAnimeRepository
import com.arifudesu.mvvmrestapi.util.Injection

class DetailAnimeVMF(
    private val application: Application,
    private val repository: DetailAnimeRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(DetailAnimeVM::class.java) ->
                    DetailAnimeVM(application, repository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: DetailAnime")
            } as T
        }

    companion object {
        @Volatile
        private var INSTANCE: DetailAnimeVMF? = null

        fun getInstance(application: Application) =
            INSTANCE ?: DetailAnimeVMF(
                application,
                Injection.provideDetailAnimeRepository(application.applicationContext)
            ).also { INSTANCE = it }
    }

    @VisibleForTesting
    fun destroyInstance() {
        INSTANCE = null
    }
}

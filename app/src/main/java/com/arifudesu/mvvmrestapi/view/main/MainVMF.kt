package com.arifudesu.mvvmrestapi.view.main

import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arifudesu.mvvmrestapi.data_source.AnimeSeasonRepository
import com.arifudesu.mvvmrestapi.util.Injection

class MainVMF(
    private val application: Application,
    private val repository: AnimeSeasonRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(MainVM::class.java) ->
                    MainVM(application, repository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: Main")
            } as T
        }

    companion object {
        @Volatile
        private var INSTANCE: MainVMF? = null

        fun getInstance(application: Application) =
            INSTANCE ?: MainVMF(
                application,
                Injection.provideAnimeSeasonRepository(application.applicationContext)

            ).also { INSTANCE = it }
    }

    @VisibleForTesting
    fun destroyInstance() {
        INSTANCE = null
    }
}

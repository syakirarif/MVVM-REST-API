package com.arifudesu.mvvmrestapi.view.dashboard.favorite

import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arifudesu.mvvmrestapi.data_source.favorite.AnimeFavoriteRepository
import com.arifudesu.mvvmrestapi.util.Injection

class AnimeFavoriteVMF(
    private val application: Application,
    private val repository: AnimeFavoriteRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(AnimeFavoriteVM::class.java) ->
                    AnimeFavoriteVM(application, repository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: AnimeFavorite")
            } as T
        }

    companion object {
        @Volatile
        private var INSTANCE: AnimeFavoriteVMF? = null

        fun getInstance(application: Application) =
            INSTANCE ?: AnimeFavoriteVMF(
                application,
                Injection.provideAnimeFavoriteRepository(application.applicationContext)

            ).also { INSTANCE = it }
    }

    @VisibleForTesting
    fun destroyInstance() {
        INSTANCE = null
    }
}

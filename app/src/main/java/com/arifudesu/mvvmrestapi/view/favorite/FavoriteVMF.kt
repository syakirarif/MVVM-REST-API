package com.arifudesu.mvvmrestapi.view.favorite

import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arifudesu.mvvmrestapi.data_source.favorite.AnimeFavoriteRepository
import com.arifudesu.mvvmrestapi.util.Injection

class FavoriteVMF(
    private val application: Application,
    private val repository: AnimeFavoriteRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(FavoriteVM::class.java) ->
                    FavoriteVM(repository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: Main")
            } as T
        }

    companion object {
        @Volatile
        private var INSTANCE: FavoriteVMF? = null

        fun getInstance(application: Application) =
            INSTANCE ?: FavoriteVMF(
                application,
                Injection.provideAnimeFavoriteRepository(application.applicationContext)

            ).also { INSTANCE = it }
    }

    @VisibleForTesting
    fun destroyInstance() {
        INSTANCE = null
    }
}

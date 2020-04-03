@file:Suppress("DEPRECATION")

package com.arifudesu.mvvmrestapi.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.arifudesu.mvvmrestapi.view.favorite.FavoriteVMF
import com.arifudesu.mvvmrestapi.view.main.MainVMF
import java.lang.Exception

fun isNetworkAvailable(context: Context): Boolean {
    try {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    } catch (e: Exception) {
        return false
    }
}

fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, frameId: Int) {
    supportFragmentManager.beginTransaction()
        .add(frameId, fragment)
        .commit()
}

private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}

fun AppCompatActivity.addFragmentToActivity(fragment: Fragment, tag: String) {
    supportFragmentManager.transact {
        add(fragment, tag)
    }
}

fun <T : ViewModel>
        AppCompatActivity.obtainMainViewModel(viewModelClass: Class<T>) =
    ViewModelProviders.of(
        this,
        MainVMF.getInstance(application)
    ).get(viewModelClass)

fun <T : ViewModel>
        AppCompatActivity.obtainFavoriteViewModel(viewModelClass: Class<T>) =
    ViewModelProviders.of(
        this,
        FavoriteVMF.getInstance(application)
    ).get(viewModelClass)
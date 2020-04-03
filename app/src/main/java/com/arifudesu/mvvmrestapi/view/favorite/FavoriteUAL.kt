package com.arifudesu.mvvmrestapi.view.favorite

import com.arifudesu.mvvmrestapi.model.AnimeFavorite

interface FavoriteUAL {

    fun onFavoriteClicked(entry: AnimeFavorite)
}
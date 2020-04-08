package com.arifudesu.mvvmrestapi.view.dashboard.favorite

import com.arifudesu.mvvmrestapi.model.AnimeFavoriteEntry


interface AnimeFavoriteUAL {

    fun onClickFavoriteButton(entry: AnimeFavoriteEntry)

    fun onClickItem(entry: AnimeFavoriteEntry)

}
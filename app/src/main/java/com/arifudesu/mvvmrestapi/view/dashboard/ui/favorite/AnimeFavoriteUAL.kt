package com.arifudesu.mvvmrestapi.view.dashboard.ui.favorite

import com.arifudesu.mvvmrestapi.model.AnimeFavoriteEntry


interface AnimeFavoriteUAL {

    fun onClickFavoriteButton(entry: AnimeFavoriteEntry)

    fun onClickItem(entry: AnimeFavoriteEntry)

}
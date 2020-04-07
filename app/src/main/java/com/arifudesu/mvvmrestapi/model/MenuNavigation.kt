package com.arifudesu.mvvmrestapi.model

import android.content.Context
import com.arifudesu.mvvmrestapi.R
import com.arifudesu.mvvmrestapi.util.navigation.NavMenuModel
import com.arifudesu.mvvmrestapi.util.navigation.NavMenuModel.SubMenuModel
import java.util.*

object MenuNavigation {

    fun getMenuNavigasi(context: Context): ArrayList<NavMenuModel> {
        val menu = ArrayList<NavMenuModel>()
        menu.add(
            NavMenuModel(
                context.resources.getString(R.string.title_season_anime),
                R.drawable.ic_anime_current_season,
                R.id.nav_main
            )
        )
        menu.add(
            NavMenuModel(
                context.resources.getString(R.string.title_favorite_anime),
                R.drawable.ic_favorite,
                R.id.nav_favorite
            )
        )
        menu.add(
            NavMenuModel(context.resources.getString(R.string.title_top_anime), R.drawable.ic_anime_top_airing,
                object : ArrayList<SubMenuModel?>() {
                    init {
                        add(SubMenuModel(context.resources.getString(R.string.title_top_anime_airing), R.id.nav_top))
                        add(SubMenuModel(context.resources.getString(R.string.title_top_anime_upcoming), R.id.nav_top_upcoming))
                    }
                })
        )
        return menu
    }

}
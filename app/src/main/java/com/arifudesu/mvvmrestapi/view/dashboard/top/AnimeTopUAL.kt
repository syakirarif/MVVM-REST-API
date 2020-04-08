package com.arifudesu.mvvmrestapi.view.dashboard.top

import com.arifudesu.mvvmrestapi.model.AnimeTopEntry
import com.arifudesu.mvvmrestapi.model.AnimeTopUpcomingEntry


interface AnimeTopUAL {

    fun onClickItem(entry: AnimeTopEntry)
    fun onClickItemUpcoming(entry: AnimeTopUpcomingEntry)

}
package com.arifudesu.mvvmrestapi.view.dashboard.top

import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arifudesu.mvvmrestapi.model.AnimeTopEntry
import com.arifudesu.mvvmrestapi.model.AnimeTopUpcomingEntry
import com.arifudesu.mvvmrestapi.view.dashboard.top.airing.AnimeTopAiringAdapter
import com.arifudesu.mvvmrestapi.view.dashboard.top.upcoming.AnimeTopUpcomingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


object AnimeTopOB {

    @BindingAdapter("app:dataList")
    @Nullable
    @JvmStatic
    fun setDataList(recyclerView: RecyclerView, list: List<AnimeTopEntry>?) {
        try {
            if (list != null) {
                with(recyclerView.adapter as AnimeTopAiringAdapter) {
                    replaceData(list)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @BindingAdapter("app:dataListUpcoming")
    @Nullable
    @JvmStatic
    fun setDataListUpcoming(recyclerView: RecyclerView, list: List<AnimeTopUpcomingEntry>?) {
        try {
            if (list != null) {
                with(recyclerView.adapter as AnimeTopUpcomingAdapter) {
                    replaceData(list)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    @BindingAdapter("app:imageUrl")
    @Nullable
    @JvmStatic
    fun setImageUrl(view: ImageView, imageUrl: String) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(
                DrawableTransitionOptions.withCrossFade(500)
            )
            .into(view)
    }

}
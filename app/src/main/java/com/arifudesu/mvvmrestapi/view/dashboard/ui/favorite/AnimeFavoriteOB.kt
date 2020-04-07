package com.arifudesu.mvvmrestapi.view.dashboard.ui.favorite

import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arifudesu.mvvmrestapi.model.AnimeFavoriteEntry
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


object AnimeFavoriteOB {

    @BindingAdapter("app:dataList")
    @Nullable
    @JvmStatic
    fun setDataList(recyclerView: RecyclerView, list: List<AnimeFavoriteEntry>?) {
        try {
            if (list != null) {
                with(recyclerView.adapter as AnimeFavoriteAdapter) {
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
package com.arifudesu.mvvmrestapi.view.main

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arifudesu.mvvmrestapi.model.AnimeEntry
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import androidx.swiperefreshlayout.widget.CircularProgressDrawable


object MainOB {

    @BindingAdapter("app:dataList")
    @JvmStatic
    fun setDataList(recyclerView: RecyclerView, list: List<AnimeEntry>?) {
        try {
            with(recyclerView.adapter as MainAdapter) {
                if (list != null) {
                    replaceData(list)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun setImageUrl(view: ImageView, imageUrl: String?) {

//        val imageUrl = listImage[0].imageUrl

        val circularProgressDrawable = CircularProgressDrawable(view.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(view.context)
            .load(imageUrl)
            .apply(
                RequestOptions()
                    .placeholder(circularProgressDrawable))
            .transition(
                DrawableTransitionOptions.withCrossFade(500)
            )
            .into(view)
    }
}
package com.arifudesu.mvvmrestapi.view.favorite

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.arifudesu.mvvmrestapi.R
import com.arifudesu.mvvmrestapi.databinding.ItemMain2Binding
import com.arifudesu.mvvmrestapi.model.AnimeFavorite
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import io.paperdb.Paper
import kotlinx.android.synthetic.main.item_main2.view.*

class FavoriteAdapter(
    var mList: LiveData<List<AnimeFavorite>>,
    var viewModel: FavoriteVM
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        Paper.init(parent.context)

        val binding = ItemMain2Binding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as MainHolder).bind(mList.value!![position], viewModel)
    }


    override fun getItemCount(): Int {
        return mList.value!!.size
    }


    fun replaceData(data: LiveData<List<AnimeFavorite>>) {
        mList = data
        notifyDataSetChanged()
    }


    class MainHolder(binding: ItemMain2Binding) :
        RecyclerView.ViewHolder(binding.root) {

        val view = binding.root


        fun bind(data: AnimeFavorite, viewModel: FavoriteVM) {

            var isFavorite = !viewModel.isThisAnimeFavorite(data.malId.toString()).equals("")

            val circularProgressDrawable = CircularProgressDrawable(view.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            with(view) {
                tv_item_main_title.text = data.title

                btn_item_main_fav_top.setOnClickListener {
                    if (!isFavorite) {
                        btn_item_main_fav_top.setBackgroundResource(R.drawable.ic_favorite)
                        btn_item_main_fav_top.setColorFilter(ContextCompat.getColor(context, R.color.red_A700), android.graphics.PorterDuff.Mode.MULTIPLY)
                    } else {
                        isFavorite = false
                        btn_item_main_fav_top.setBackgroundResource(R.drawable.ic_favorite_border)
                        btn_item_main_fav_top.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY)
                    }

                }

                lyt_parent.setOnClickListener {
                    Toast.makeText(context, "${data.title}", Toast.LENGTH_SHORT).show()
                }

                Glide.with(context)
                    .load(data.imageUrl)
                    .apply(
                        RequestOptions()
                            .placeholder(circularProgressDrawable)
                    )
                    .transition(
                        DrawableTransitionOptions.withCrossFade(500)
                    )
                    .into(view.img_item_main_poster)
            }

        }

    }

}
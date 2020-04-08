package com.arifudesu.mvvmrestapi.view.dashboard.favorite

import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arifudesu.mvvmrestapi.R
import com.arifudesu.mvvmrestapi.databinding.ItemAnimeFavoriteBinding
import com.arifudesu.mvvmrestapi.model.AnimeFavoriteEntry

class AnimeFavoriteAdapter(
    var mList: List<AnimeFavoriteEntry>,
    val mViewModel: AnimeFavoriteVM
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemAnimeFavoriteBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_anime_favorite,
            parent,
            false
        )

        return AnimeFavoriteHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val datas = mList[position]

        val actionListener = object : AnimeFavoriteUAL {
            override fun onClickFavoriteButton(entry: AnimeFavoriteEntry) {
                Toast.makeText(holder.itemView.context, "Favorite Clicked", Toast.LENGTH_SHORT).show()
            }

            override fun onClickItem(entry: AnimeFavoriteEntry) {
                Toast.makeText(holder.itemView.context, "${entry.title}", Toast.LENGTH_SHORT).show()
            }
        }

        (holder as AnimeFavoriteHolder).bind(mList[position], actionListener)
    }


    override fun getItemCount(): Int {
        return mList.size
    }


    fun replaceData(data: List<AnimeFavoriteEntry>) {
        mList = data
        notifyDataSetChanged()
    }


    class AnimeFavoriteHolder(binding: ItemAnimeFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val mBinding = binding

        fun bind(data: AnimeFavoriteEntry, listener: AnimeFavoriteUAL) {
            mBinding.datas = data
            mBinding.action = listener
            mBinding.executePendingBindings()
        }

    }

}
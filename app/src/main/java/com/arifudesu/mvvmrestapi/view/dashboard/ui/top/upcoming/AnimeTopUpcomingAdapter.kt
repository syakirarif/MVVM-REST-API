package com.arifudesu.mvvmrestapi.view.dashboard.ui.top.upcoming

import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arifudesu.mvvmrestapi.R
import com.arifudesu.mvvmrestapi.databinding.ItemAnimeTopUpcomingBinding
import com.arifudesu.mvvmrestapi.model.AnimeTopEntry
import com.arifudesu.mvvmrestapi.model.AnimeTopUpcomingEntry
import com.arifudesu.mvvmrestapi.view.dashboard.ui.top.AnimeTopUAL
import com.arifudesu.mvvmrestapi.view.dashboard.ui.top.airing.AnimeTopAiringVM

class AnimeTopUpcomingAdapter(
    var mList: List<AnimeTopUpcomingEntry>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemAnimeTopUpcomingBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_anime_top_upcoming,
            parent,
            false
        )

        return AnimeTopHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val datas = mList[position]

        val actionListener = object : AnimeTopUAL {
            override fun onClickItem(entry: AnimeTopEntry) {

            }

            override fun onClickItemUpcoming(entry: AnimeTopUpcomingEntry) {

            }

        }

        (holder as AnimeTopHolder).bind(mList[position], actionListener)
    }


    override fun getItemCount(): Int {
        return mList.size
    }


    fun replaceData(data: List<AnimeTopUpcomingEntry>) {
        mList = data
        notifyDataSetChanged()
    }


    class AnimeTopHolder(binding: ItemAnimeTopUpcomingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val mBinding = binding

        fun bind(data: AnimeTopUpcomingEntry, listener: AnimeTopUAL) {
            mBinding.datas = data
            mBinding.action = listener
            mBinding.executePendingBindings()
        }

    }

}
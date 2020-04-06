package com.arifudesu.mvvmrestapi.view.main

import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arifudesu.mvvmrestapi.databinding.ItemMainBinding
import com.arifudesu.mvvmrestapi.R
import com.arifudesu.mvvmrestapi.model.AnimeEntry

class MainAdapter(
    var mList: List<AnimeEntry>,
    val mViewModel: MainVM
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemMainBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_main,
            parent,
            false
        )

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val datas = mList[position]

        val actionListener = object : MainUAL {
            override fun onClickItem(entry: AnimeEntry) {
                mViewModel.openData.value = entry
            }
//            override fun onProdiClicked(entry: FakultasProdiEntry) {
//                mViewModel.openData.value = entry
//            }
        }

        (holder as MainHolder).bind(mList[position], actionListener)
    }


    override fun getItemCount(): Int {
        return mList.size
    }


    fun replaceData(data: List<AnimeEntry>) {
        mList = data
        notifyDataSetChanged()
    }


    class MainHolder(binding: ItemMainBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val mBinding = binding

        fun bind(data: AnimeEntry, listener: MainUAL) {
            mBinding.datas = data
            mBinding.action = listener
            mBinding.executePendingBindings()
        }

    }

}
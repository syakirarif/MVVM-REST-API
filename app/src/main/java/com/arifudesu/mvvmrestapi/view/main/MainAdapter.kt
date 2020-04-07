package com.arifudesu.mvvmrestapi.view.main

import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arifudesu.mvvmrestapi.databinding.ItemMainBinding
import com.arifudesu.mvvmrestapi.R
import com.arifudesu.mvvmrestapi.model.AnimeEntry

class MainAdapter(
    var mList: List<AnimeEntry>,
    val mViewModel: MainVM
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    private val viewModel: MainVM = mViewModel

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
//                Toast.makeText(holder.itemView.context, "${entry.title}", Toast.LENGTH_SHORT).show()
//                mViewModel.insertAnimeFavorite(entry)
            }

            override fun onClickFavorite(entry: AnimeEntry) {
                mViewModel.openFavorite.value = entry
//                Toast.makeText(
//                    holder.itemView.context,
//                    "Favorite ${entry.malId}",
//                    Toast.LENGTH_SHORT
//                ).show()

            }
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
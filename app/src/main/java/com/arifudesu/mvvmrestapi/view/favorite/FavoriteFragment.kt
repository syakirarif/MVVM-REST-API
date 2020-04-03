package com.arifudesu.mvvmrestapi.view.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.arifudesu.mvvmrestapi.databinding.FragmentFavoriteBinding
import com.arifudesu.mvvmrestapi.util.SpacingItemDecoration
import com.arifudesu.mvvmrestapi.util.Tools
import com.arifudesu.mvvmrestapi.view.dashboard.DashboardActivity

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {

    private lateinit var adapternya: FavoriteAdapter
    private lateinit var viewModel: FavoriteVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = FragmentFavoriteBinding.inflate(
            inflater, container, false
        ).apply {
            viewModel = (activity as DashboardActivity).obtainFavoriteViewModel()
        }

        val anime = viewModel.getAnimeFavorite()

        adapternya = FavoriteAdapter(anime, viewModel)

        binding.rvFavorite.apply {
            adapter = adapternya
            layoutManager = GridLayoutManager(this.context, 2)
            addItemDecoration(SpacingItemDecoration(2, Tools.dpToPx(this.context, 3), true))
            setHasFixedSize(true)
        }

        return binding.root
    }

}

package com.arifudesu.mvvmrestapi.view.dashboard.favorite


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.arifudesu.mvvmrestapi.R
import com.arifudesu.mvvmrestapi.databinding.FragmentAnimeFavoriteBinding
import com.arifudesu.mvvmrestapi.view.dashboard.DashboardActivity
import com.orhanobut.hawk.Hawk


class AnimeFavoriteFragment : Fragment() {

    private lateinit var adapter: AnimeFavoriteAdapter
    private lateinit var viewBinding: FragmentAnimeFavoriteBinding

    private lateinit var itemString: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = FragmentAnimeFavoriteBinding.inflate(
            inflater, container, false
        ).apply {
            viewModel = (activity as DashboardActivity).obtainsAnimeFavoriteVM()
        }

        viewBinding.let {
            it.viewModel = viewBinding.viewModel
            it.setLifecycleOwner(this@AnimeFavoriteFragment)
        }

        viewBinding.rvFragmentAnimeFavorite.apply {
            layoutManager = GridLayoutManager(this@AnimeFavoriteFragment.context, 2)
        }

        itemString = getString(R.string.title_favorite_anime)
        subscribeUi()
        return viewBinding.root
    }

    private fun subscribeUi() {
        viewBinding.viewModel?.dataListLive?.observe(viewLifecycleOwner) { result ->
            viewBinding.hasFavorites = !result.isNotEmpty()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViewModel()
    }

    private fun setupViewModel() {
        val viewModel = viewBinding.viewModel

        if (viewModel != null) {
            adapter = AnimeFavoriteAdapter(ArrayList(), viewModel)
            viewBinding.rvFragmentAnimeFavorite.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()

        viewBinding.viewModel?.getAnimeFavorite()

    }

    override fun onPause() {
        super.onPause()

        Hawk.put("PREV_FRAGMENT", itemString)
    }

    companion object {
        fun newInstance() = AnimeFavoriteFragment().apply {

        }

    }

}

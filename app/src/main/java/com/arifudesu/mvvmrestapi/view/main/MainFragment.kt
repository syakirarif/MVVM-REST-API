package com.arifudesu.mvvmrestapi.view.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.arifudesu.mvvmrestapi.databinding.FragmentMainBinding
import com.arifudesu.mvvmrestapi.util.SpacingItemDecoration
import com.arifudesu.mvvmrestapi.util.Tools
import com.arifudesu.mvvmrestapi.util.isNetworkAvailable
import com.arifudesu.mvvmrestapi.view.dashboard.DashboardActivity
import com.arifudesu.mvvmrestapi.view.favorite.FavoriteVM
import kotlinx.android.synthetic.main.fragment_main.view.*


class MainFragment : Fragment() {

    private lateinit var adapternya: MainAdapter
    private lateinit var viewBinding: FragmentMainBinding
    private lateinit var viewModel: MainVM
    private lateinit var viewModelFavorite: FavoriteVM

    private lateinit var refreshEntry: SwipeRefreshLayout

    private val seasonYear = "2020"
    private val seasonName = "spring"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = FragmentMainBinding.inflate(
            inflater, container, false
        ).apply {
            viewModel = (activity as DashboardActivity).obtainMainViewModel()
            viewModelFavorite = (activity as DashboardActivity).obtainFavoriteViewModel()
        }

//        viewBinding.let {
//            it.viewModel = viewBinding.viewModel
//            it.setLifecycleOwner(this@MainFragment)
//        }

        val isRefresh = isNetworkAvailable(context!!)
//        val isRefresh = false

        viewModel.start(seasonYear, seasonName, isRefresh)

        adapternya = MainAdapter(ArrayList(), viewModelFavorite)

        viewBinding.root.rv_fragment_main.apply {
            adapter = adapternya
            layoutManager = GridLayoutManager(this@MainFragment.context, 2)
            addItemDecoration(SpacingItemDecoration(2, Tools.dpToPx(requireActivity(), 3), true))
            setHasFixedSize(true)
        }

        refreshEntry = viewBinding.mainLayoutRefresh
        refreshEntry.setOnRefreshListener {
            viewModel.onRefresh(seasonYear, seasonName)
        }

        refreshEntry.isRefreshing = false

        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViewModel()
    }

    private fun setupViewModel() {
//        val vm = ViewModelProvider(this)[FavoriteVM::class.java]
        adapternya = MainAdapter(ArrayList(), viewModelFavorite)
        viewBinding.rvFragmentMain.adapter = adapternya
    }

    override fun onResume() {
        super.onResume()

        refreshEntry.isRefreshing = true

        val isRefresh = isNetworkAvailable(context!!)
//        val isRefresh = false

        viewModel.start(seasonYear, seasonName, isRefresh)
    }

    companion object {
        fun newInstance() = MainFragment().apply {

        }

    }

}

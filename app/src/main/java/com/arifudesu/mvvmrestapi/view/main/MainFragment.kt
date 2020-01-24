package com.arifudesu.mvvmrestapi.view.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.arifudesu.mvvmrestapi.databinding.FragmentMainBinding
import com.arifudesu.mvvmrestapi.util.isNetworkAvailable
import kotlinx.android.synthetic.main.fragment_main.view.*


class MainFragment : Fragment() {

    private lateinit var adapter: MainAdapter
    private lateinit var viewBinding: FragmentMainBinding

    private lateinit var refreshEntry: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = FragmentMainBinding.inflate(
            inflater, container, false
        ).apply {
            viewModel = (activity as MainActivity).obtainViewModel()
        }

        viewBinding.let {
            it.viewModel = viewBinding.viewModel
            it.setLifecycleOwner(this@MainFragment)
        }

        viewBinding.root.rv_fragment_main.apply {
            layoutManager = GridLayoutManager(this@MainFragment.context, 2)
        }

        refreshEntry = viewBinding.mainLayoutRefresh

        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViewModel()
    }

    private fun setupViewModel() {
        val viewModel = viewBinding.viewModel

        if (viewModel != null) {
            adapter = MainAdapter(ArrayList(), viewModel)
            viewBinding.rvFragmentMain.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()

        refreshEntry.isRefreshing = true

        val seasonYear = "2020"
        val seasonName = "winter"
        val isRefresh = isNetworkAvailable(context!!)
//        val isRefresh = false

        viewBinding.viewModel?.start(seasonYear, seasonName, isRefresh)
    }

    companion object {
        fun newInstance() = MainFragment().apply {

        }

    }

}

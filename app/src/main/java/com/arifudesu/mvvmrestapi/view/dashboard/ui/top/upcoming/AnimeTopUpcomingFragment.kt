package com.arifudesu.mvvmrestapi.view.dashboard.ui.top.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.arifudesu.mvvmrestapi.databinding.FragmentAnimeTopUpcomingBinding
import com.arifudesu.mvvmrestapi.view.dashboard.DashboardActivity
import com.orhanobut.hawk.Hawk

/**
 * A simple [Fragment] subclass.
 */
class AnimeTopUpcomingFragment : Fragment() {

    private lateinit var adapter: AnimeTopUpcomingAdapter
    private lateinit var viewBinding: FragmentAnimeTopUpcomingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentAnimeTopUpcomingBinding.inflate(
            inflater, container, false
        ).apply {
            viewModel = (activity as DashboardActivity).obtainsAnimeTopUpcomingVM()
        }

        viewBinding.let {
            it.viewModel = viewBinding.viewModel
            it.setLifecycleOwner(this@AnimeTopUpcomingFragment)
        }

        viewBinding.rvFragmentAnimeTopUpcoming.apply {
            layoutManager = GridLayoutManager(this@AnimeTopUpcomingFragment.context, 2)
        }


        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        setupViewModel()
    }

    override fun onStart() {
        super.onStart()
//        setupViewModel()
    }

    private fun setupViewModel() {
        val viewModel = viewBinding.viewModel

        if (viewModel != null) {
            adapter = AnimeTopUpcomingAdapter(ArrayList())
            viewBinding.rvFragmentAnimeTopUpcoming.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()

        setupViewModel()

        var needRefresh = true

        if (Hawk.contains("IS_TOP_UPCOMING_NEED_REFRESH"))
            needRefresh = Hawk.get("IS_TOP_UPCOMING_NEED_REFRESH")

        viewBinding.viewModel?.start("anime", "1", "upcoming", needRefresh)
    }

}

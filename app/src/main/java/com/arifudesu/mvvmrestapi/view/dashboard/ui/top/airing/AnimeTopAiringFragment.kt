package com.arifudesu.mvvmrestapi.view.dashboard.ui.top.airing


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.arifudesu.mvvmrestapi.R
import com.arifudesu.mvvmrestapi.databinding.FragmentAnimeTopAiringBinding
import com.arifudesu.mvvmrestapi.view.dashboard.DashboardActivity
import com.orhanobut.hawk.Hawk


class AnimeTopAiringFragment : Fragment() {

    private lateinit var adapter: AnimeTopAiringAdapter
    private lateinit var viewBinding: FragmentAnimeTopAiringBinding

    private lateinit var itemString: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = FragmentAnimeTopAiringBinding.inflate(
            inflater, container, false
        ).apply {
            viewModel = (activity as DashboardActivity).obtainsAnimeTopVM()
        }

        viewBinding.let {
            it.viewModel = viewBinding.viewModel
            it.setLifecycleOwner(this@AnimeTopAiringFragment)
        }

        viewBinding.rvFragmentAnimeTop.apply {
            layoutManager = GridLayoutManager(this@AnimeTopAiringFragment.context, 2)
        }

        itemString = getString(R.string.title_top_anime_airing)

        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onPause() {
        super.onPause()
        Hawk.put("PREV_FRAGMENT", itemString)
    }

    override fun onStart() {
        super.onStart()
//        setupViewModel()
    }

    private fun setupViewModel() {
        val viewModel = viewBinding.viewModel

        if (viewModel != null) {
            adapter =
                AnimeTopAiringAdapter(
                    ArrayList()
                )
            viewBinding.rvFragmentAnimeTop.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()

        setupViewModel()

        var needRefresh = true

        if (Hawk.contains("IS_TOP_NEED_REFRESH"))
            needRefresh = Hawk.get("IS_TOP_NEED_REFRESH")

        viewBinding.viewModel?.start("anime", "1", "airing", needRefresh)
    }

    companion object {
        fun newInstance() = AnimeTopAiringFragment().apply {

        }

    }

}

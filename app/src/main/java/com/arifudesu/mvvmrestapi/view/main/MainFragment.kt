package com.arifudesu.mvvmrestapi.view.main


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.arifudesu.mvvmrestapi.databinding.FragmentMainBinding
import com.arifudesu.mvvmrestapi.util.isNetworkAvailable
import com.arifudesu.mvvmrestapi.view.dashboard.DashboardActivity
import com.jaredrummler.materialspinner.MaterialSpinner
import com.jaredrummler.materialspinner.MaterialSpinnerAdapter
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.fragment_main.view.*
import java.util.*
import kotlin.collections.ArrayList

private val LIST_YEAR = listOf(
    "2020",
    "2019",
    "2018",
    "2017",
    "2016",
    "2015"
)

private var LIST_SEASON = listOf(
    "Spring",
    "Summer",
    "Fall",
    "Winter"
)


class MainFragment : Fragment() {

    private lateinit var adapter: MainAdapter
    private lateinit var viewBinding: FragmentMainBinding

    private lateinit var refreshEntry: SwipeRefreshLayout

    private lateinit var spYear: MaterialSpinner
    private lateinit var spSeason: MaterialSpinner

    private var selectedYear: String = "2020"
    private var selectedSeason: String = "spring"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = FragmentMainBinding.inflate(
            inflater, container, false
        ).apply {
            viewModel = (activity as DashboardActivity).obtainViewModel()
        }

        viewBinding.let {
            it.viewModel = viewBinding.viewModel
            it.setLifecycleOwner(this@MainFragment)
        }

        viewBinding.root.rv_fragment_main.apply {
            layoutManager = GridLayoutManager(this@MainFragment.context, 2)
        }

        Hawk.init(viewBinding.root.context).build()

        refreshEntry = viewBinding.mainLayoutRefresh
        spYear = viewBinding.spYear
        spYear.setAdapter(MaterialSpinnerAdapter(requireActivity(), LIST_YEAR))
        spSeason = viewBinding.spSeason
        spSeason.setAdapter(MaterialSpinnerAdapter(requireActivity(), LIST_SEASON))

        spYear.setOnItemSelectedListener { _, _, _, _ ->
            retrieveData(true)
        }

        spSeason.setOnItemSelectedListener { _, _, _, _ ->
            retrieveData(true)
        }

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

//        val doneBefore: Boolean = Hawk.contains("GET_LIST_DONE")
//        val isRefresh = Hawk.contains("IS_MAIN_NEED_REFRESH") ?: true
        var needRefresh = true

        if (Hawk.contains("IS_MAIN_NEED_REFRESH"))
            needRefresh = Hawk.get("IS_MAIN_NEED_REFRESH")

        retrieveData(needRefresh)
    }

    private fun retrieveData(needRefresh: Boolean) {
        Log.e("retrieveData()", "isRefresh: ${needRefresh}")
//        Toast.makeText(requireContext(), "isRefresh: ${needRefresh}", Toast.LENGTH_SHORT).show()
        refreshEntry.isRefreshing = true

        selectedYear = LIST_YEAR[spYear.selectedIndex]
        selectedSeason = LIST_SEASON[spSeason.selectedIndex].toLowerCase(Locale.getDefault())

        viewBinding.viewModel?.start(selectedYear, selectedSeason, needRefresh)
    }

    companion object {
        fun newInstance() = MainFragment().apply {

        }

    }

}

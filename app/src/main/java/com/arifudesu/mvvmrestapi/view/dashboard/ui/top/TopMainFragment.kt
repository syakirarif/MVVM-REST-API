package com.arifudesu.mvvmrestapi.view.dashboard.ui.top

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.arifudesu.mvvmrestapi.databinding.FragmentTopMainBinding
import com.arifudesu.mvvmrestapi.view.dashboard.ui.top.airing.AnimeTopAiringFragment
import com.arifudesu.mvvmrestapi.view.dashboard.ui.top.upcoming.AnimeTopUpcomingFragment
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems

/**
 * A simple [Fragment] subclass.
 */
class TopMainFragment : Fragment() {

    private lateinit var viewBinding: FragmentTopMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentTopMainBinding.inflate(inflater, container, false)

        val adapter = FragmentPagerItemAdapter(
            requireActivity().supportFragmentManager, FragmentPagerItems.with(requireActivity())
                .add("AIRING", AnimeTopAiringFragment::class.java)
                .add("UPCOMING", AnimeTopUpcomingFragment::class.java)
                .create()
        )

        val viewPager = viewBinding.vpTop
        viewPager.adapter = adapter
        viewBinding.vptabTop.setViewPager(viewPager)

        return viewBinding.root
    }

}

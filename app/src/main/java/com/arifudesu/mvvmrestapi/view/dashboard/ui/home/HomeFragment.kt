package com.arifudesu.mvvmrestapi.view.dashboard.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.arifudesu.mvvmrestapi.R
import com.arifudesu.mvvmrestapi.view.dashboard.ui.gallery.GalleryFragment
import com.arifudesu.mvvmrestapi.view.dashboard.ui.slideshow.SlideshowFragment
import com.arifudesu.mvvmrestapi.view.main.MainFragment
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val adapter = FragmentPagerItemAdapter(
            requireActivity().supportFragmentManager, FragmentPagerItems.with(requireActivity())
                .add("GALLERY", GalleryFragment::class.java)
                .add("SLIDE", SlideshowFragment::class.java)
                .add("MAIN", MainFragment::class.java)
                .create()
        )

        val viewPager = root.vp_home
        viewPager.adapter = adapter
        root.vptab_home.setViewPager(viewPager)
        return root
    }
}

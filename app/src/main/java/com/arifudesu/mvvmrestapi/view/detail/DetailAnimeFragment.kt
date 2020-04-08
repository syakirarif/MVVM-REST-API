package com.arifudesu.mvvmrestapi.view.detail


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.arifudesu.mvvmrestapi.R
import com.arifudesu.mvvmrestapi.databinding.FragmentDetailAnimeBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.orhanobut.hawk.Hawk


class DetailAnimeFragment : Fragment() {

    private lateinit var viewBinding: FragmentDetailAnimeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewBinding = FragmentDetailAnimeBinding.inflate(
            inflater, container, false
        ).apply {
            viewModel = (activity as DetailActivity).obtainsDetailAnimeVM()
        }

        viewBinding.let {
            it.viewModel = viewBinding.viewModel
            it.setLifecycleOwner(this@DetailAnimeFragment)
        }

        subscribeUi()
        initToolbar()
        setHasOptionsMenu(true)

        return viewBinding.root
    }

    private fun initToolbar() {
        viewBinding.toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }
    }

    private fun subscribeUi() {
        viewBinding.viewModel!!.entries.observe(viewLifecycleOwner) { result ->
            viewBinding.tvDetailTitle.text = result.title

            val circularProgressDrawable = CircularProgressDrawable(requireContext())
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            Glide.with(requireContext())
                .load(result.imageUrl)
                .apply(
                    RequestOptions()
                        .placeholder(circularProgressDrawable)
                )
                .transition(
                    DrawableTransitionOptions.withCrossFade(500)
                )
                .into(viewBinding.imgDetailPoster)

        }
    }

    override fun onResume() {
        super.onResume()

        val malId = Hawk.get<String>("SELECTED_ANIME_MAL_ID")
        Log.e("DetailAnimeFragment", "MAL_ID: ${malId}")
        viewBinding.viewModel?.getDetailAnime(malId)
    }

    companion object {
        fun newInstance() = DetailAnimeFragment().apply {

        }

    }

}

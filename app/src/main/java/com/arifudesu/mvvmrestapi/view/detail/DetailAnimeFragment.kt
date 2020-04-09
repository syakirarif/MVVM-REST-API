package com.arifudesu.mvvmrestapi.view.detail


import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import java.lang.StringBuilder


class DetailAnimeFragment : Fragment() {

    private lateinit var viewBinding: FragmentDetailAnimeBinding

    private var playWhenReady = true
    private var currentWindow: Int = 0
    private var playbackPosition: Long = 0

    private var link: String = ""


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
            link = result.trailerUrl.toString()

            if (result == null)
                viewBinding.tvDetailTitle.text = getString(R.string.loading)

            viewBinding.btnTrailer.visibility = View.VISIBLE
            viewBinding.btnTrailer.setOnClickListener {
                requireActivity().startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
            }

            viewBinding.tvDetailTitle.text = result.title
            viewBinding.tvDetailTitleSynonyms.text = StringBuilder(result.titleJapanese + "\n" + result.titleEnglish)
            viewBinding.tvDetailSynopsis.text = result.synopsis
            viewBinding.tvDetailPremiered.text = result.premiered
            viewBinding.tvDetailStatus.text = StringBuilder("Status: " + result.status)

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

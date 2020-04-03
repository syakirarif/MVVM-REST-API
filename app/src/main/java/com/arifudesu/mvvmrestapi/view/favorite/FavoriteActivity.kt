package com.arifudesu.mvvmrestapi.view.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.arifudesu.mvvmrestapi.databinding.ActivityMainBinding
import com.arifudesu.mvvmrestapi.util.SpacingItemDecoration
import com.arifudesu.mvvmrestapi.util.Tools
import com.arifudesu.mvvmrestapi.util.obtainFavoriteViewModel
import kotlinx.android.synthetic.main.activity_favorite.view.*

class FavoriteActivity : AppCompatActivity() {

    private lateinit var adapternya: FavoriteAdapter
    private lateinit var viewModel: FavoriteVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        viewModel = ViewModelProvider(this)[FavoriteVM::class.java]
        viewModel = obtainViewModel()

        val anime = viewModel.getAnimeFavorite()

        adapternya = FavoriteAdapter(anime, viewModel)

        view.rv_favorite.apply {
            adapter = adapternya
            layoutManager = GridLayoutManager(this.context, 2)
            addItemDecoration(SpacingItemDecoration(2, Tools.dpToPx(this.context, 3), true))
            setHasFixedSize(true)
        }
    }

    fun obtainViewModel(): FavoriteVM = obtainFavoriteViewModel(FavoriteVM::class.java)
}

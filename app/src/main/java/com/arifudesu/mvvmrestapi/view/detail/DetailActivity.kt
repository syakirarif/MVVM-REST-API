package com.arifudesu.mvvmrestapi.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arifudesu.mvvmrestapi.R
import com.arifudesu.mvvmrestapi.util.obtainDetailAnimeViewModel
import com.orhanobut.hawk.Hawk

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailAnimeVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        Hawk.init(this).build()

//        val extras = intent.extras
//
//        if (extras != null) {
//            val malId = extras.getString(EXTRA_MAL_ID)
//        }

        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = obtainsDetailAnimeVM().apply {

        }
    }

    fun obtainsDetailAnimeVM(): DetailAnimeVM = obtainDetailAnimeViewModel(
        DetailAnimeVM::class.java
    )

//    companion object {
//        const val EXTRA_MAL_ID = "extra_mal_id"
//    }
}

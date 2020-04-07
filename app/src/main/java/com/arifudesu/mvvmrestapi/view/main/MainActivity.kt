package com.arifudesu.mvvmrestapi.view.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.arifudesu.mvvmrestapi.databinding.ActivityMainBinding
import com.arifudesu.mvvmrestapi.model.AnimeEntry
import com.arifudesu.mvvmrestapi.util.obtainMainViewModel
import com.arifudesu.mvvmrestapi.util.replaceFragmentInActivity

class MainActivity : AppCompatActivity(), MainUAL {

    private lateinit var viewModel: MainVM
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupFragment()
        setupViewModel()
    }

    private fun setupFragment() {
        supportFragmentManager.findFragmentById(binding.frameMain.id)

        replaceFragmentInActivity(MainFragment.newInstance(), binding.frameMain.id)
    }

    private fun setupViewModel() {
        viewModel = obtainViewModel().apply {
            openData.observe(this@MainActivity, Observer { entry ->
                onClickItem(entry!!)
            })
        }
    }

    fun obtainViewModel(): MainVM = obtainMainViewModel(MainVM::class.java)

    override fun onClickItem(entry: AnimeEntry) {
//        Toast.makeText(this, "${entry.title}", Toast.LENGTH_SHORT).show()
    }

    override fun onClickFavorite(entry: AnimeEntry) {

    }
}

package com.arifudesu.mvvmrestapi.view.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.arifudesu.mvvmrestapi.R
import com.arifudesu.mvvmrestapi.model.AnimeEntry
import com.arifudesu.mvvmrestapi.util.obtainMainViewModel
import com.arifudesu.mvvmrestapi.util.replaceFragmentInActivity

class MainActivity : AppCompatActivity(), MainUAL {

    private lateinit var viewModel: MainVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupFragment()
        setupViewModel()
    }

    private fun setupFragment() {
        supportFragmentManager.findFragmentById(R.id.frameMain)

        replaceFragmentInActivity(MainFragment.newInstance(), R.id.frameMain)
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
        Toast.makeText(this, "${entry.title}", Toast.LENGTH_SHORT).show()
    }
}

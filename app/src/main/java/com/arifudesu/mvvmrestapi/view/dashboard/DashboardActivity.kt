package com.arifudesu.mvvmrestapi.view.dashboard

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.arifudesu.mvvmrestapi.R
import com.arifudesu.mvvmrestapi.model.AnimeEntry
import com.arifudesu.mvvmrestapi.util.obtainAnimeTopViewModel
import com.arifudesu.mvvmrestapi.util.obtainMainViewModel
import com.arifudesu.mvvmrestapi.view.dashboard.ui.top.airing.AnimeTopAiringVM
import com.arifudesu.mvvmrestapi.view.dashboard.ui.top.upcoming.AnimeTopUpcomingVM
import com.arifudesu.mvvmrestapi.view.main.MainUAL
import com.arifudesu.mvvmrestapi.view.main.MainVM
import com.google.android.material.navigation.NavigationView
import com.orhanobut.hawk.Hawk

class DashboardActivity : AppCompatActivity(), MainUAL {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var viewModel: MainVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        Hawk.init(this).build()

        setupViewModel()

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_top_main, R.id.nav_main, R.id.nav_top, R.id.nav_top_upcoming
                ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.dashboard, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun obtainViewModel(): MainVM = obtainMainViewModel(MainVM::class.java)

    fun obtainsAnimeTopVM(): AnimeTopAiringVM = obtainAnimeTopViewModel(
        AnimeTopAiringVM::class.java)

    fun obtainsAnimeTopUpcomingVM(): AnimeTopUpcomingVM = obtainAnimeTopViewModel(
        AnimeTopUpcomingVM::class.java)

    private fun setupViewModel() {
        viewModel = obtainViewModel().apply {
            openData.observe(this@DashboardActivity, Observer { entry ->
                onClickItem(entry!!)
            })
        }
    }

    override fun onClickItem(entry: AnimeEntry) {
        Toast.makeText(this, "${entry.title}", Toast.LENGTH_SHORT).show()
    }
}

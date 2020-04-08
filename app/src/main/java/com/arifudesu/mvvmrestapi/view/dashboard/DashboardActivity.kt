package com.arifudesu.mvvmrestapi.view.dashboard

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arifudesu.mvvmrestapi.R
import com.arifudesu.mvvmrestapi.model.AnimeEntry
import com.arifudesu.mvvmrestapi.model.MenuNavigation
import com.arifudesu.mvvmrestapi.util.navigation.NavMenuAdapter
import com.arifudesu.mvvmrestapi.util.navigation.NavMenuModel
import com.arifudesu.mvvmrestapi.util.navigation.SubTitle
import com.arifudesu.mvvmrestapi.util.navigation.TitleMenu
import com.arifudesu.mvvmrestapi.util.obtainAnimeFavoriteViewModel
import com.arifudesu.mvvmrestapi.util.obtainAnimeTopViewModel
import com.arifudesu.mvvmrestapi.util.obtainMainViewModel
import com.arifudesu.mvvmrestapi.view.dashboard.favorite.AnimeFavoriteVM
import com.arifudesu.mvvmrestapi.view.dashboard.top.airing.AnimeTopAiringVM
import com.arifudesu.mvvmrestapi.view.dashboard.top.upcoming.AnimeTopUpcomingVM
import com.arifudesu.mvvmrestapi.view.dashboard.main.MainUAL
import com.arifudesu.mvvmrestapi.view.dashboard.main.MainVM
import com.google.android.material.navigation.NavigationView
import com.orhanobut.hawk.Hawk
import java.util.*

class DashboardActivity : AppCompatActivity(), MainUAL, NavMenuAdapter.MenuItemClickListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var viewModel: MainVM
    private lateinit var menu: ArrayList<NavMenuModel>
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private lateinit var adapter: NavMenuAdapter
    private lateinit var navMenuDrawer: RecyclerView

    private lateinit var currentItemString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        Hawk.init(this).build()

        setupViewModel()

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_main, R.id.nav_favorite, R.id.nav_top, R.id.nav_top_upcoming
            ), drawerLayout
        )
//        appBarConfiguration = AppBarConfiguration.Builder(navController.graph).build()
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        setNavigationDrawerMenu()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.dashboard, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
//        navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun obtainViewModel(): MainVM = obtainMainViewModel(MainVM::class.java)

    fun obtainsAnimeFavoriteVM(): AnimeFavoriteVM = obtainAnimeFavoriteViewModel(AnimeFavoriteVM::class.java)

    fun obtainsAnimeTopVM(): AnimeTopAiringVM = obtainAnimeTopViewModel(
        AnimeTopAiringVM::class.java
    )

    fun obtainsAnimeTopUpcomingVM(): AnimeTopUpcomingVM = obtainAnimeTopViewModel(
        AnimeTopUpcomingVM::class.java
    )

    private fun setupViewModel() {
        viewModel = obtainViewModel().apply {
            openData.observe(this@DashboardActivity, Observer { entry ->
                onClickItem(entry!!)
            })
            openFavorite.observe(this@DashboardActivity, Observer { entry ->
                onClickFavorite(entry)
            })
        }
    }

    override fun onClickItem(entry: AnimeEntry) {
        Toast.makeText(this, "${entry.title}", Toast.LENGTH_SHORT).show()
    }

    override fun onClickFavorite(entry: AnimeEntry) {
        Toast.makeText(this, "Favorite: ${entry.malId}", Toast.LENGTH_SHORT).show()
        viewModel.insertAnimeFavorite(entry)
    }

    override fun onMenuItemClick(itemString: String?) {
        for (i in menu.indices) {
            if (itemString == menu[i].menuTitle) {
                navController.navigate(menu[i].fragment)
                break
            } else {
                for (j in 0 until menu[i].subMenu.size) {
                    if (itemString == menu[i].subMenu[j].subMenuTitle) {
                        navController.navigate(menu[i].subMenu[j].fragment)
                        break
                    }
                }
            }
        }

        if (drawerLayout != null) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    private fun updateNavAdapter(itemString: String?) {
        Log.e("updateNavAdapter", "PREV_FRAGMENT: ${itemString}")
        for (i in menu.indices) {
            if (itemString == menu[i].menuTitle) {
                navMenuDrawer.adapter = adapter
                adapter.selectedItemParent = itemString
                adapter.notifyDataSetChanged()
                break
            } else {
                for (j in 0 until menu[i].subMenu.size) {
                    if (itemString == menu[i].subMenu[j].subMenuTitle) {
                        navMenuDrawer.adapter = adapter
                        adapter.selectedItemParent = itemString
                        adapter.notifyDataSetChanged()
                        break
                    }
                }
            }
        }
    }

    private fun setNavigationDrawerMenu() {
        adapter = NavMenuAdapter(this, getMenuList(), this)
        navMenuDrawer = findViewById<View>(R.id.rv_nav_menu_dashboard) as RecyclerView
        navMenuDrawer.adapter = adapter
        navMenuDrawer.layoutManager = LinearLayoutManager(this)
        //        INITIATE SELECT MENU
        adapter.selectedItemParent = menu[0].menuTitle
        onMenuItemClick(adapter.selectedItemParent)
        adapter.notifyDataSetChanged()
    }

    private fun getMenuList(): List<TitleMenu>? {
        val list: MutableList<TitleMenu> = ArrayList<TitleMenu>()
        menu = MenuNavigation.getMenuNavigasi(this)
        for (i in menu.indices) {
            val subMenu: ArrayList<SubTitle> = ArrayList<SubTitle>()
            if (menu[i].subMenu.size > 0) {
                for (j in 0 until menu[i].subMenu.size) {
                    subMenu.add(SubTitle(menu[i].subMenu[j].subMenuTitle))
                }
            }
            list.add(TitleMenu(menu[i].menuTitle, subMenu, menu[i].menuIconDrawable))
        }
        return list
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            updateNavAdapter(Hawk.get("PREV_FRAGMENT"))
//            onMenuItemClick(Hawk.get("CURRENT_FRAGMENT"))
            super.onBackPressed()
        }
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id = item.itemId
//        if (id == android.R.id.home) {
//            drawerLayout.openDrawer(GravityCompat.START)
//            return true
//        }
//
//        return super.onOptionsItemSelected(item)
//    }
}

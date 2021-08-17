package com.jesusrojo.pagingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jesusrojo.pagingdemo.databinding.ActivityMainLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController:NavController
    private lateinit var binding: ActivityMainLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setupBottomNav(navController)
    }

    private fun setupBottomNav(navController: NavController) {
        val bottomNav = binding.bottomNavigation as BottomNavigationView
        bottomNav.setupWithNavController(navController)

        /*
        The set of destinations by id considered at the top level of your information hierarchy.
        The Up button will not be displayed when on these destinations.
         */
        val appBarConfig = AppBarConfiguration(setOf(
            R.id.nav_paging_source,
            R.id.nav_remote_mediator
        ))
        /*
       By calling this method, the title in the action bar will automatically be updated when
       the destination changes
        */
        setupActionBarWithNavController(navController,appBarConfig)
    }

    override fun onDestroy() {
        destroyLeekCanary()
        super.onDestroy()
    }

    // LEAK CANARY
    private fun destroyLeekCanary() {
        val app = application as MyApplication
        app.mustDieLeakCanary(this)
    }
}
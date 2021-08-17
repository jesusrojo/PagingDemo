package com.jesusrojo.pagingdemo.ghrepos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.jesusrojo.pagingdemo.MyApplication
import com.jesusrojo.pagingdemo.R
import com.jesusrojo.pagingdemo.databinding.ActivityMainGhreposBinding
import com.jesusrojo.pagingdemo.ghrepos.presentation.ui.GHReposFragment
import com.jesusrojo.pagingdemo.ghrepos.presentation.ui.adapter.GHReposAdapter

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainGHReposActivity : AppCompatActivity() {

    @Inject lateinit var ghReposAdapter: GHReposAdapter
    private lateinit var binding: ActivityMainGhreposBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainGhreposBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment: NavHostFragment = supportFragmentManager
                .findFragmentById(R.id.fragment_container_view_main) as NavHostFragment

        val navController: NavController = navHostFragment.navController

        val appBarConfiguration: AppBarConfiguration = AppBarConfiguration(navController.graph)

        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
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

    //FROM FRAGMENTS - MENU OPTIONS
    fun setUpMenuOptionsDetailsFragment() = binding.toolbar.menu.clear()

    fun setUpMenuOptionsMainFragment() {
        binding.toolbar.menu.clear()
        binding.toolbar.inflateMenu(R.menu.menu_top_right_ghrepos)
        binding.toolbar.setOnMenuItemClickListener(OnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_item_1 -> {
                    ghReposAdapter.orderByName()
                    toast("By Name")
                    return@OnMenuItemClickListener true
                }
                R.id.menu_item_2 -> {
                    ghReposAdapter.orderByStars()
                    toast("By Stars")
                    return@OnMenuItemClickListener true
                }
                R.id.menu_item_3 -> {
                    ghReposAdapter.orderByForks()
                    toast("By Forks")
                    return@OnMenuItemClickListener true
                }
                R.id.menu_item_4 -> {
                    ghReposAdapter.orderByAuthorName()
                    toast("By Author Name")
                    return@OnMenuItemClickListener true
                }
                R.id.menu_item_5 -> {
                    deleteAllLocal()
                    toast("Delete All Local")
                    return@OnMenuItemClickListener true
                }
            }
            return@OnMenuItemClickListener false
        })
    }

    // TOAST
    private fun toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

     // TO FRAGMENT
    private fun deleteAllLocal() {
        val navHostFragment: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container_view_main) as NavHostFragment
        val currentFragment = navHostFragment.childFragmentManager.fragments[0]
        val ghReposFragment: GHReposFragment = currentFragment as GHReposFragment
        ghReposFragment.deleteAllLocal()
    }
}
package com.dicoding.picodiploma.eatnowcapstone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dicoding.picodiploma.eatnowcapstone.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_home)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_search, R.id.navigation_account
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setOnItemSelectedListener { menuItem ->
            val handled = when (menuItem.itemId) {
                R.id.nav_home -> {
                    navController.navigate(R.id.navigation_home)
                    true
                }
                R.id.nav_search -> {
                    navController.navigate(R.id.navigation_search)
                    true
                }
                R.id.nav_akun -> {
                    navController.navigate(R.id.navigation_account)
                    true
                }
                else -> false
            }
            handled
        }
    }
}

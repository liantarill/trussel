package com.tam.trussel

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.tam.trussel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val nav = findViewById<BottomNavigationView>(R.id.nav_view)
        nav.itemIconTintList = null
        nav.itemTextColor = null

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_forYou, R.id.navigation_sell,R.id.navigation_chat, R.id.navigation_profile
            )
        )
        navView.setupWithNavController(navController)



        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home -> navView.visibility = View.VISIBLE
                R.id.navigation_forYou -> navView.visibility = View.VISIBLE
                R.id.navigation_sell -> navView.visibility = View.VISIBLE
                R.id.navigation_chat -> navView.visibility = View.VISIBLE
                R.id.navigation_profile -> navView.visibility = View.VISIBLE
                else -> navView.visibility = View.GONE
            }
        }

    }
}
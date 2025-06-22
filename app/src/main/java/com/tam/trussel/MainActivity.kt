package com.tam.trussel

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.tam.trussel.databinding.ActivityMainBinding
import com.tam.trussel.ui.auth.SessionManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        sessionManager = SessionManager(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navView.itemIconTintList = null
        navView.itemTextColor = null

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_forYou, R.id.navigation_sell, R.id.navigation_chat, R.id.navigation_profile
            )
        )
        navView.setupWithNavController(navController)

        if (!sessionManager.isLoggedIn()) {
            navController.navigate(R.id.loginFragment)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home,
                R.id.navigation_forYou,
                R.id.navigation_sell,
                R.id.navigation_chat,
                R.id.navigation_profile -> navView.visibility = View.VISIBLE
                else -> navView.visibility = View.GONE
            }
        }
    }

    // Untuk menangani tombol back
    override fun onBackPressed() {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        when (navController.currentDestination?.id) {
            R.id.navigation_home -> {
                moveTaskToBack(true)
            }
            else -> super.onBackPressed()
        }
    }
}
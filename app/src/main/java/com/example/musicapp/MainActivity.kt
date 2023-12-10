package com.example.musicapp

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.musicapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var listener: NavController.OnDestinationChangedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_songs,
                R.id.navigation_albums,
                R.id.navigation_artists,
                R.id.navigation_playlists
            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        listener =
            NavController.OnDestinationChangedListener { controller, destination, arguments ->
                when (destination.id) {
                    R.id.navigation_home -> {
                        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.black)))
                    }

                    R.id.navigation_songs -> {
                        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.black)))
                    }

                    R.id.navigation_albums -> {
                        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.black)))
                    }

                    R.id.navigation_artists -> {
                        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.black)))
                    }

                    R.id.navigation_playlists -> {
                        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.black)))
                    }
                }
            }
    }

    override fun onResume() {
        super.onResume()
        if (this::navController.isInitialized) {
            navController.addOnDestinationChangedListener(listener)
        }
    }

    override fun onPause() {
        super.onPause()
        if (this::navController.isInitialized) {
            navController.removeOnDestinationChangedListener(listener)
        }
    }
}
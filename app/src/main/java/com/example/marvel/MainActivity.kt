package com.example.marvel

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.marvel.databinding.ActivityMainBinding
import com.example.comics.R as comicsR
import com.example.series.R as seriesR

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val navViewsFragments = listOf(
                R.id.navigation_character,
                R.id.charactersFragment,
                comicsR.id.comicsFragment,
                seriesR.id.seriesFragment
            )

            if (navViewsFragments.contains(destination.id))
                navView.visibility = View.VISIBLE
            else
                navView.visibility = View.GONE
        }
    }
}
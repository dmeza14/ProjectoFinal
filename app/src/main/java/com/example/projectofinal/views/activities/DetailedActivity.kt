package com.example.projectofinal.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.projectofinal.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class DetailedActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.inner_nav_host) as NavHostFragment
        val navController = navHostFragment.findNavController()
        val appBarConfiguration: AppBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_all, R.id.nav_recientes, R.id.nav_favoritos
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setupWithNavController(navController)

    }

}
package com.example.projectofinal.views.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.projectofinal.R
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView

class PokeListFragment : Fragment(R.layout.fragment_poke_list) {

    private lateinit var innerBottomNavigationView: BottomNavigationView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.inner_nav_graph) as NavHostFragment
        val navController = navHostFragment.navController
        innerBottomNavigationView.setupWithNavController(navController)
    }

}
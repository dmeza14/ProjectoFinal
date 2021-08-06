package com.example.projectofinal.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.projectofinal.R
import com.example.projectofinal.db.PokemonEntity
import com.example.projectofinal.viewmodels.FavoritePokemonViewModel
import com.example.projectofinal.views.adapters.PokemonListAdapter

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {
    private lateinit var viewModel : FavoritePokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(FavoritePokemonViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        }

    }

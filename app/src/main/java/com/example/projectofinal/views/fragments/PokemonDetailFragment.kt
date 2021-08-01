package com.example.projectofinal.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.projectofinal.R
import com.example.projectofinal.viewmodels.PokemonViewModel
import com.squareup.picasso.Picasso


class PokemonDetailFragment : Fragment(R.layout.fragment_pokemon_detail) {
    val args: PokemonDetailFragmentArgs by navArgs()
    private lateinit var normalPokeImageView: ImageView
    private lateinit var shinyPokeImageView: ImageView
    private lateinit var pokeDescriptionTextView: TextView
    private lateinit var viewModel: PokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        normalPokeImageView = view.findViewById(R.id.normalPokeImageView)
        shinyPokeImageView = view.findViewById(R.id.shinyPokeImageView)
        pokeDescriptionTextView = view.findViewById(R.id.pokeCaracteristicasTextView)
        viewModel.fetchPokemonData(args.id)
        viewModel.pokemonLiveData.observe(viewLifecycleOwner){
            //pintamos la información

            Picasso.get()
                .load(it.sprites.front_shiny)
                .resize(500,500)
                .into(shinyPokeImageView)
            Picasso.get()
                .load(it.sprites.front_default)
                .resize(500,500)
                .into(normalPokeImageView)
        }


    }


}
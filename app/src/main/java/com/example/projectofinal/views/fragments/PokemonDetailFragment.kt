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
import com.example.projectofinal.viewmodels.PokemonDescriptionViewModel
import com.example.projectofinal.viewmodels.PokemonViewModel
import com.squareup.picasso.Picasso


class PokemonDetailFragment : Fragment(R.layout.fragment_pokemon_detail) {
    val args: PokemonDetailFragmentArgs by navArgs()
    private lateinit var normalPokeImageView: ImageView
    private lateinit var shinyPokeImageView: ImageView
    private lateinit var pokeDescriptionTextView: TextView
    private lateinit var pokeDescriptionViewModel: PokemonDescriptionViewModel
    private lateinit var pokemonViewModel: PokemonViewModel
    private lateinit var pokeType1: TextView
    private lateinit var pokeType2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pokemonViewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
        pokeDescriptionViewModel = ViewModelProvider(this).get(PokemonDescriptionViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        normalPokeImageView = view.findViewById(R.id.normalPokeImageView)
        shinyPokeImageView = view.findViewById(R.id.shinyPokeImageView)
        pokeDescriptionTextView = view.findViewById(R.id.pokeCaracteristicasTextView)
        pokeType1 = view.findViewById(R.id.slot1)
        pokeType2 = view.findViewById(R.id.slot2)
        pokemonViewModel.fetchPokemonData(args.id)
        pokemonViewModel.pokemonLiveData.observe(viewLifecycleOwner) {
            //pintamos la información
            Picasso.get()
                .load(it.sprites.front_shiny)
                .resize(400, 400)
                .into(shinyPokeImageView)
            Picasso.get()
                .load(it.sprites.front_default)
                .resize(400, 400)
                .into(normalPokeImageView)
            //Tipo de pokemon
            pokeType1.text = it.types[0].type.name
            if(it.types.size !== 1){
                pokeType2.text = it.types[1].type.name
            }


        }

        pokeDescriptionViewModel.fetchPokemonDescription(args.id)
        pokeDescriptionViewModel.pokemonDescriptionLiveData.observe(viewLifecycleOwner) {
            //pintamos la información
            pokeDescriptionTextView.text = it.descriptions[1].description
        }

    }

}
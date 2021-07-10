package com.example.projectofinal.views.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.example.projectofinal.R
import com.example.projectofinal.views.adapters.PokemonListAdapter
import com.example.projectofinal.views.models.PokemonList

class AllFragment : Fragment(R.layout.fragment_all) {
    private lateinit var pokemonRecylerView: RecyclerView
    //Declaramos el adapater
    private val adapter = PokemonListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Inflamos el RecyclerView y asignamos el adapter
        pokemonRecylerView = view.findViewById(R.id.pokemon_recycler_view)
        pokemonRecylerView.addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))
        pokemonRecylerView.adapter = adapter

        adapter.pokemonList = getDummyPokeList()
    }

    private fun getDummyPokeList() : List<PokemonList> {
        return listOf(
            PokemonList("Abomasnow", "Grass", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/460.png"),
            PokemonList("Abra", "Psychic", "https://static.wikia.nocookie.net/espokemon/images/f/f6/Abra.png/revision/latest?cb=20080901113000"),
            PokemonList("Absol", "Dark", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/359.png"),
            PokemonList("Accelgor", "Bug", "https://cdn2.bulbagarden.net/upload/thumb/3/34/617Accelgor.png/1200px-617Accelgor.png"),
            PokemonList("Aegislash", "Steel", "https://images.wikidexcdn.net/mwuploads/wikidex/8/86/latest/20190423163814/Aegislash.png"),
            PokemonList("Aerodactyl", "Rock", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/142.png"),
            PokemonList("Aggron", "Steel", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/306.png"),
            PokemonList("Aipom", "Normal", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/190.png"),
            PokemonList("Alakazam", "Psychic", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/065.png"),
            PokemonList("Alcremie", "Fairy", "https://images.wikidexcdn.net/mwuploads/wikidex/thumb/4/4d/latest/20190709232245/Alcremie.png/1200px-Alcremie.png")
        )
    }

}
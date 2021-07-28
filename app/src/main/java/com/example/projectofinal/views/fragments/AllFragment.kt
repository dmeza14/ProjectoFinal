package com.example.projectofinal.views.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.example.projectofinal.R
import com.example.projectofinal.network.models.Results
import com.example.projectofinal.viewmodels.PokemonViewModel
import com.example.projectofinal.views.adapters.PokemonListAdapter
import com.example.projectofinal.network.models.PokemonList

class AllFragment : Fragment(R.layout.fragment_all) {
    private lateinit var pokemonRecylerView: RecyclerView
    private lateinit var viewModel: PokemonViewModel

    //Declaramos el adapater
    private val adapter = PokemonListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Inflamos el RecyclerView y asignamos el adapter
        pokemonRecylerView = view.findViewById(R.id.pokemon_recycler_view)
        pokemonRecylerView.addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))
        pokemonRecylerView.adapter = adapter

        viewModel.fetchPokemonList(100, 0)
        viewModel.pokemonListLiveData.observe(viewLifecycleOwner) {
            //pintamos la información
            adapter.pokemonList = newPokelist(it.results)

        }
    }

    private fun newPokelist(names: List<Results>): List<PokemonList> {
        val list = ArrayList<PokemonList>()
        for (i in names.indices) {
            val item = PokemonList(
                names[i].name,
                "Pokemon número ${i + 1}",
                "https://pokeres.bastionbot.org/images/pokemon/${i + 1}.png"
            ) //this number is offset+1
            list += item
        }
        return list
    }
}

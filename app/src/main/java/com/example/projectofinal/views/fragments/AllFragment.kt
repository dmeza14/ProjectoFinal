package com.example.projectofinal.views.fragments

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.example.projectofinal.R
import com.example.projectofinal.network.models.Results
import com.example.projectofinal.viewmodels.PokemonViewModel
import com.example.projectofinal.views.adapters.PokemonListAdapter
import com.example.projectofinal.network.models.PokemonList
import com.google.android.material.snackbar.Snackbar

class AllFragment : Fragment(R.layout.fragment_all), PokemonListAdapter.OnItemClickListener {
    private lateinit var pokemonRecyclerView: RecyclerView
    private lateinit var viewModel: PokemonViewModel
    private lateinit var progressBar: ProgressBar
    private var pokemonID: Int = 0

    //Declaramos el adapater
    private val adapter = PokemonListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Inflamos el RecyclerView y asignamos el adapter
        pokemonRecyclerView = view.findViewById(R.id.pokemon_recycler_view)
        progressBar = view.findViewById((R.id.progressBar))
        pokemonRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))
        pokemonRecyclerView.adapter = adapter

        viewModel.fetchPokemonList(100, 0)
        viewModel.pokemonListLiveData.observe(viewLifecycleOwner) {
            //pintamos la información
            adapter.pokemonList = newPokelist(it.results)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.serverError.observe(viewLifecycleOwner) {
            Snackbar.make(
                view,
                requireContext().getString(R.string.server_error_message),
                Snackbar.LENGTH_LONG
            ).show()
        }

    }

    override fun onItemClick(position: Int) {
        //pasamos el ID del pokemon seleccionado en cada celda para obtener su información
        val action = AllFragmentDirections.actionNavAllToPokemonDetailFragment(position + 1)
        findNavController().navigate(action)
    }

    //Function to fill the list that is used in the recycler view.
    private fun newPokelist(names: List<Results>): List<PokemonList> {
        var list = listOf<PokemonList>()
        for (i in names.indices) {
            val item = PokemonList(
                names[i].name,
                "Pokemon número ${i + 1}",
                //this number is offset+1
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${i + 1}.png"
            )
            list += item
        }
        return list
    }


}

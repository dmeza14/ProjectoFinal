package com.example.projectofinal.views.fragments

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
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
    private lateinit var pokemonRecylerView: RecyclerView
    private lateinit var viewModel: PokemonViewModel
    private lateinit var progressBar: ProgressBar

    //Declaramos el adapater
    private val adapter = PokemonListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Inflamos el RecyclerView y asignamos el adapter
        pokemonRecylerView = view.findViewById(R.id.pokemon_recycler_view)
        progressBar = view.findViewById((R.id.progressBar))
        pokemonRecylerView.addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))
        pokemonRecylerView.adapter = adapter

        viewModel.fetchPokemonList(500, 0)
        viewModel.pokemonListLiveData.observe(viewLifecycleOwner) {
            //pintamos la información
            adapter.pokemonList = newPokelist(it.results)
        }

        viewModel.isLoading.observe(viewLifecycleOwner){
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.serverError.observe(viewLifecycleOwner){
            Snackbar.make(view, requireContext().getString(R.string.server_error_message), Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onItemClick(position: Int) {
        val action = AllFragmentDirections.actionNavAllToPokemonDetailFragment()
        findNavController().navigate(action)
    }

    //Function to fill the list that is used in the recycler view.
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

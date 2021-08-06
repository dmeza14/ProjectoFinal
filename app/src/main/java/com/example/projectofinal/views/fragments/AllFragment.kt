package com.example.projectofinal.views.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
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
import com.example.projectofinal.viewmodels.AllPokemonViewModel
import com.google.android.material.snackbar.Snackbar

class AllFragment : Fragment(R.layout.fragment_all), PokemonListAdapter.OnItemClickListener {
    private lateinit var pokemonRecyclerView: RecyclerView
    private lateinit var viewModel: PokemonViewModel
    private lateinit var progressBar: ProgressBar
    private lateinit var sharedPref: SharedPreferences
    private lateinit var saludarEntrenador: TextView

    //ViewModel para base de datos agregar a favoritos
    private lateinit var allPokemonViewModel: AllPokemonViewModel

    //Declaramos el adapater
    private val adapter = PokemonListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
        allPokemonViewModel = ViewModelProvider(this).get(AllPokemonViewModel::class.java)
        sharedPref = requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Inflamos el RecyclerView y asignamos el adapter
        pokemonRecyclerView = view.findViewById(R.id.pokemon_recycler_view)
        progressBar = view.findViewById((R.id.progressBar))
        pokemonRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))
        pokemonRecyclerView.adapter = adapter
        saludarEntrenador = view.findViewById(R.id.saludarEntrenador)
        //Ajustar manualmente la cantidad de Pokemones a desplegar en el recycler view
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

    override fun onResume() {
        super.onResume()
        //Usando los valores del sign in - determinamos el saludo dependiendo si es chico o chica
        val saludo = sharedPref.getString("ENTRENADOR_NAME", "") ?: ""
        val esMasculino = sharedPref.getBoolean("MASCULINO_CHECKED", true) ?: true
        val esFemenino = sharedPref.getBoolean("FEMENINO_CHECKED", false) ?: false
        if (esMasculino == true){
            saludarEntrenador.text = requireContext().getString(R.string.saludar_entrenador_text, saludo)
            saludarEntrenador.visibility = if (saludo.isEmpty()) View.GONE else View.VISIBLE
        }else if (esFemenino == true){
            saludarEntrenador.text = requireContext().getString(R.string.saludar_entrenadora_text, saludo)
            saludarEntrenador.visibility = if (saludo.isEmpty()) View.GONE else View.VISIBLE
        }else{
            saludarEntrenador.visibility = View.GONE
        }

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

package com.example.projectofinal.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectofinal.db.PokemonDB
import com.example.projectofinal.db.PokemonEntity
import com.example.projectofinal.db.PokemonReposiroty
import kotlinx.coroutines.launch

class AllPokemonViewModel(application: Application): AndroidViewModel(application) {
    private lateinit var reposiroty: PokemonReposiroty

    init {
        val db = PokemonDB.getDatabase(application)
        reposiroty = PokemonReposiroty(db.pokemonDAO())
    }

    fun insert(name: String) = viewModelScope.launch {
        reposiroty.insert(PokemonEntity(name))
    }
}
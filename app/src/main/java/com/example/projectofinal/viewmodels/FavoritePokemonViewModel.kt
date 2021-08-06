package com.example.projectofinal.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.projectofinal.db.PokemonDB
import com.example.projectofinal.db.PokemonEntity
import com.example.projectofinal.db.PokemonReposiroty
import kotlinx.coroutines.launch

class FavoritePokemonViewModel(application: Application): AndroidViewModel(application) {
    private lateinit var reposiroty: PokemonReposiroty

    init {
        val db = PokemonDB.getDatabase(application)
        reposiroty = PokemonReposiroty(db.pokemonDAO())
    }

    fun getAllPokemonsByName(name: String) : LiveData<List<PokemonEntity>>{
        return reposiroty.getAllPokemonsByName(name)

    }

    fun delete(name: String) = viewModelScope.launch {
        reposiroty.delete(PokemonEntity(name))
    }
}
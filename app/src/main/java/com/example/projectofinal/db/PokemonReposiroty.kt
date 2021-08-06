package com.example.projectofinal.db

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class PokemonReposiroty(private val pokemonDAO: PokemonDAO) {

    fun getAllPokemonsByName(name: String) : LiveData<List<PokemonEntity>> {
        return pokemonDAO.getPokemonByName()
    }
    @WorkerThread
    suspend fun insert(pokemonEntity: PokemonEntity){
        pokemonDAO.insertPokemon(pokemonEntity)
    }

    @WorkerThread
    suspend fun delete(pokemonEntity: PokemonEntity){
        pokemonDAO.deletePokemon(pokemonEntity)
    }

}
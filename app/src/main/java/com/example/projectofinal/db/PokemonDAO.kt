package com.example.projectofinal.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PokemonDAO {
    @Query("Select * from pokemonentity")
    fun getPokemonByName() : LiveData<List<PokemonEntity>>

    @Insert
    suspend fun insertPokemon(pokemonEntity: PokemonEntity)

    @Delete
    suspend fun deletePokemon(pokemonEntity: PokemonEntity)
}
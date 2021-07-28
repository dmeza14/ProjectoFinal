package com.example.projectofinal.network

import com.example.projectofinal.network.models.PokemonListResponse
import com.example.projectofinal.network.models.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//Creamos la interface donde vamos a declarar los diferentes metodos HTTP
interface ApiService {
    @GET("pokemon")
    fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ) : Call<PokemonListResponse>

    @GET("pokemon/{id}")
    fun getPokemon(
        @Path("id") name: Int
    ): Call<PokemonResponse>

}
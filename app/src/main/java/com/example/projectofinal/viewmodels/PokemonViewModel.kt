package com.example.projectofinal.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectofinal.network.RetrofitProvider
import com.example.projectofinal.network.models.PokemonListResponse
import com.example.projectofinal.network.models.PokemonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonViewModel: ViewModel() {

    private val retrofitProvider = RetrofitProvider()

    //privado
    private val _pokemonLiveData = MutableLiveData<PokemonResponse>()
    //testing the list call
    private val _pokemonListLiveData = MutableLiveData<PokemonListResponse>()

    //público
    val pokemonLiveData: LiveData<PokemonResponse> = _pokemonLiveData
    //testing the list call
    val pokemonListLiveData: LiveData<PokemonListResponse> = _pokemonListLiveData
    //Pokemon response to get pokemon details
    fun fetchPokemonData(id: Int) {
        retrofitProvider.getApiService()
            .getPokemon(id)
            .enqueue(object : Callback<PokemonResponse> {
                override fun onResponse(
                    call: Call<PokemonResponse>,
                    response: Response<PokemonResponse>
                ) {
                    if (response.isSuccessful) {
                        _pokemonLiveData.postValue(response.body())
                    } else {
                        //servidor falla, por varias razones, por ejemplo no se armo bien el request
                    }
                }

                override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                    //servidor falla, no se pudo conectar
                    val a = ""
                }

            })
    }
    //List response to get the names
    fun fetchPokemonList(limit: Int, offset: Int) {
        retrofitProvider.getApiService()
            .getPokemonList(limit,offset)
            .enqueue(object : Callback<PokemonListResponse>{
                override fun onResponse(
                    call: Call<PokemonListResponse>,
                    response: Response<PokemonListResponse>
                ) {
                    if (response.isSuccessful) {
                        _pokemonListLiveData.postValue(response.body())
                    } else {
                        //servidor falla, por varias razones, por ejemplo no se armo bien el request
                    }
                }

                override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                    //servidor falla, no se pudo conectar
                }

            })

    }
}
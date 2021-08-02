package com.example.projectofinal.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectofinal.network.RetrofitProvider
import com.example.projectofinal.network.models.PokemonDescriptionResponse
import com.example.projectofinal.network.models.PokemonListResponse
import com.example.projectofinal.network.models.PokemonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonViewModel: ViewModel() {

    private val retrofitProvider = RetrofitProvider()

    //privado
    private val _pokemonLiveData = MutableLiveData<PokemonResponse>()
    //público
    val pokemonLiveData: LiveData<PokemonResponse> = _pokemonLiveData

    //testing the list call
    private val _pokemonListLiveData = MutableLiveData<PokemonListResponse>()
    //testing the list call
    val pokemonListLiveData: LiveData<PokemonListResponse> = _pokemonListLiveData

    //For Pokemon description
    private val _pokemonDescriptionLiveData = MutableLiveData<PokemonDescriptionResponse>()
    val pokemonDescriptionLiveData: LiveData<PokemonDescriptionResponse> = _pokemonDescriptionLiveData


    //Progress bar loading
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    //Server error
    private val _serverError = MutableLiveData<Boolean>()
    val serverError: LiveData<Boolean> = _serverError


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
                        _serverError.postValue(true)
                        //servidor falla, por varias razones, por ejemplo no se armo bien el request
                    }
                }

                override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                    //servidor falla, no se pudo conectar
                    _serverError.postValue(true)
                }

            })
    }
    //List response to get the names
    fun fetchPokemonList(limit: Int, offset: Int) {
        _isLoading.postValue(true)
        retrofitProvider.getApiService()
            .getPokemonList(limit,offset)
            .enqueue(object : Callback<PokemonListResponse>{
                override fun onResponse(
                    call: Call<PokemonListResponse>,
                    response: Response<PokemonListResponse>
                ) {
                    _isLoading.postValue(false)
                    if (response.isSuccessful) {
                        _pokemonListLiveData.postValue(response.body())
                    } else {
                        _serverError.postValue(true)
                        //servidor falla, por varias razones, por ejemplo no se armo bien el request
                    }
                }

                override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                    _isLoading.postValue(false)
                    _serverError.postValue(true)
                    //servidor falla, no se pudo conectar
                }

            })

    }

    //For the pokemon description
    fun fetchPokemonDescription(id: Int) {
        //_isLoading.postValue(true)
        retrofitProvider.getApiService()
            .getPokemonDescription(id)
            .enqueue(object : Callback<PokemonDescriptionResponse>{
                override fun onResponse(
                    call: Call<PokemonDescriptionResponse>,
                    response: Response<PokemonDescriptionResponse>
                ) {
                    _isLoading.postValue(false)
                    if (response.isSuccessful) {
                        _pokemonDescriptionLiveData.postValue(response.body())
                    } else {
                       // _serverError.postValue(true)
                        //servidor falla, por varias razones, por ejemplo no se armo bien el request
                    }
                }

                override fun onFailure(call: Call<PokemonDescriptionResponse>, t: Throwable) {
                   // _isLoading.postValue(false)
                   // _serverError.postValue(true)
                    //servidor falla, no se pudo conectar
                }

            })

    }
}
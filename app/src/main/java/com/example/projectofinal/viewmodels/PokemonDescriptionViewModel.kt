package com.example.projectofinal.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectofinal.network.RetrofitProvider
import com.example.projectofinal.network.models.PokemonDescriptionResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonDescriptionViewModel: ViewModel() {
    private val retrofitProvider = RetrofitProvider()
    //For Pokemon description
    private val _pokemonDescriptionLiveData = MutableLiveData<PokemonDescriptionResponse>()
    val pokemonDescriptionLiveData: LiveData<PokemonDescriptionResponse> = _pokemonDescriptionLiveData

    //For the pokemon description
    fun fetchPokemonDescription(id: Int) {
        //_isLoading.postValue(true)
        retrofitProvider.getApiService()
            .getPokemonDescription(id)
            .enqueue(object : Callback<PokemonDescriptionResponse> {
                override fun onResponse(
                    call: Call<PokemonDescriptionResponse>,
                    response: Response<PokemonDescriptionResponse>
                ) {

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
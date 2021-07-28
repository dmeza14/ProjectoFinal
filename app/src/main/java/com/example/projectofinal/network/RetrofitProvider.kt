package com.example.projectofinal.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Creamos el provider de retrofir para abstraer la inicializacion
class RetrofitProvider {
    private var retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

   fun getApiService() : ApiService = retrofit.create(ApiService::class.java)
}
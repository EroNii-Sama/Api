package com.moviles.practicaapi.repositories

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitRepository {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://personas.jmacboy.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
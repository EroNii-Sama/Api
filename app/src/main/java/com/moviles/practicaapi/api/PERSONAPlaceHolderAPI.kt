package com.moviles.practicaapi.api

import com.moviles.practicaapi.models.Persona
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PERSONAPlaceHolderAPI {
    @GET("personas")
    fun getPersonas(): Call<List<Persona>>

    @GET("personas/{id}")
    fun getPersona(@Path("id") id: Int): Call<Persona>

    @POST("personas")
    fun createPersona(@Body persona: Persona): Call<Persona>

    @POST("personas/{id}")
    fun updatePersona(@Path("id") id: Int, @Body persona: Persona): Call<Persona>

    @POST("personas/{id}/delete")
    fun deletePersona(@Path("id") id: Int): Call<Persona>
}
package com.moviles.practicaapi.api

import com.moviles.practicaapi.models.Persona
import com.moviles.practicaapi.models.Post
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface JSONPlaceHolderAPI {
    @GET("posts/{id}")
    fun getPost(@Path("id") id: Int): Call<Post>

    @GET("posts")
    fun getPosts(): Call<List<Post>>
}
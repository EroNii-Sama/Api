package com.moviles.practicaapi.repositories

import com.moviles.practicaapi.api.JSONPlaceHolderAPI
import com.moviles.practicaapi.models.Post
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostRepository {

    fun getPosts(listener: PostListListener) {
        val retrofit = RetrofitRepository.getRetrofit()
        val jsonPlaceHolderAPI = retrofit.create(JSONPlaceHolderAPI::class.java)
        jsonPlaceHolderAPI.getPosts().enqueue(object : retrofit2.Callback<List<Post>> {
            override fun onResponse(
                call: Call<List<Post>>,
                response: retrofit2.Response<List<Post>>
            ) {
                if (response.isSuccessful) {
                    listener.onPostListSuccess(response.body())
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                listener.onPostListFailure(t)
            }
        })
    }

    fun getPost(id: Int, listener: PostByIdListener) {
        val retrofit = RetrofitRepository.getRetrofit()

        val service: JSONPlaceHolderAPI =
            retrofit.create(JSONPlaceHolderAPI::class.java)
        service.getPost(id).enqueue(object : retrofit2.Callback<Post> {
            override fun onResponse(
                call: Call<Post>,
                response: retrofit2.Response<Post>
            ) {
                if (response.isSuccessful) {
                    listener.onPostByIdSuccess(response.body())
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                listener.onPostByIdFailure(t)
            }
        })
    }

    interface PostByIdListener {
        fun onPostByIdSuccess(post: Post?)
        fun onPostByIdFailure(t: Throwable)
    }

    interface PostListListener {
        fun onPostListSuccess(posts: List<Post>?)
        fun onPostListFailure(t: Throwable)
    }
}
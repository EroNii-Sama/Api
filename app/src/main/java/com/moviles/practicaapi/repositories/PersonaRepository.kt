package com.moviles.practicaapi.repositories

import com.moviles.practicaapi.api.PERSONAPlaceHolderAPI
import com.moviles.practicaapi.models.Persona
import com.moviles.practicaapi.ui.activities.FormPersona
import retrofit2.Call

object PersonaRepository {
    fun getPersonas(listener: PersonaListListener) {
        val retrofit = RetrofitRepository.getRetrofit()
        val personaPlaceHolderAPI = retrofit.create(PERSONAPlaceHolderAPI::class.java)
        personaPlaceHolderAPI.getPersonas().enqueue(object : retrofit2.Callback<List<Persona>> {
            override fun onResponse(
                call: Call<List<Persona>>,
                response: retrofit2.Response<List<Persona>>
            ) {
                if (response.isSuccessful) {
                    listener.onPersonaListSuccess(response.body())
                }
            }

            override fun onFailure(call: Call<List<Persona>>, t: Throwable) {
                listener.onPersonaListFailure(t)
            }
        })
    }

    fun getPersona(id: Int, listener: PersonaByIdListener) {
        val retrofit = RetrofitRepository.getRetrofit()

        val service: PERSONAPlaceHolderAPI =
            retrofit.create(PERSONAPlaceHolderAPI::class.java)
        service.getPersona(id).enqueue(object : retrofit2.Callback<Persona> {
            override fun onResponse(
                call: Call<Persona>,
                response: retrofit2.Response<Persona>
            ) {
                if (response.isSuccessful) {
                    listener.onPersonaByIdSuccess(response.body())
                }
            }

            override fun onFailure(call: Call<Persona>, t: Throwable) {
                listener.onPersonaByIdFailure(t)
            }
        })
    }

    fun createPersona(persona: Persona, listener: PersonaCreateListener) {
        val retrofit = RetrofitRepository.getRetrofit()
        val personaPlaceHolderAPI = retrofit.create(PERSONAPlaceHolderAPI::class.java)
        personaPlaceHolderAPI.createPersona(persona).enqueue(object : retrofit2.Callback<Persona> {
            override fun onResponse(
                call: Call<Persona>,
                response: retrofit2.Response<Persona>
            ) {
                if (response.isSuccessful) {
                    listener.onPersonaCreateSuccess(response.body())
                }
            }

            override fun onFailure(call: Call<Persona>, t: Throwable) {
                listener.onPersonaCreateFailure(t)
            }
        })
    }

    fun updatePersona(id: Int, persona: Persona, listener: PersonaUpdateListener) {
        val retrofit = RetrofitRepository.getRetrofit()
        val personaPlaceHolderAPI = retrofit.create(PERSONAPlaceHolderAPI::class.java)
        personaPlaceHolderAPI.updatePersona(id, persona)
            .enqueue(object : retrofit2.Callback<Persona> {
                override fun onResponse(
                    call: Call<Persona>,
                    response: retrofit2.Response<Persona>
                ) {
                    if (response.isSuccessful) {
                        listener.onPersonaUpdateSuccess(response.body())
                    }
                }

                override fun onFailure(call: Call<Persona>, t: Throwable) {
                    listener.onPersonaUpdateFailure(t)
                }
            })
    }

    fun deletePersona(id: Int, listener: PersonaDeleteListener) {
        val retrofit = RetrofitRepository.getRetrofit()
        val personaPlaceHolderAPI = retrofit.create(PERSONAPlaceHolderAPI::class.java)
        personaPlaceHolderAPI.deletePersona(id).enqueue(object : retrofit2.Callback<Persona> {
            override fun onResponse(
                call: Call<Persona>,
                response: retrofit2.Response<Persona>
            ) {
                if (response.isSuccessful) {
                    listener.onPersonaDeleteSuccess(response.body())
                }
            }

            override fun onFailure(call: Call<Persona>, t: Throwable) {
                listener.onPersonaDeleteFailure(t)
            }
        })
    }

    interface PersonaByIdListener {
        fun onPersonaByIdSuccess(post: Persona?)
        fun onPersonaByIdFailure(t: Throwable)
    }

    interface PersonaListListener {
        fun onPersonaListSuccess(posts: List<Persona>?)
        fun onPersonaListFailure(t: Throwable)
    }

    interface PersonaCreateListener {
        fun onPersonaCreateSuccess(post: Persona?)
        fun onPersonaCreateFailure(t: Throwable)
    }

    interface PersonaUpdateListener {
        fun onPersonaUpdateSuccess(post: Persona?)
        fun onPersonaUpdateFailure(t: Throwable)
    }

    interface PersonaDeleteListener {
        fun onPersonaDeleteSuccess(post: Persona?)
        fun onPersonaDeleteFailure(t: Throwable)
    }
}


package com.moviles.practicaapi.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.moviles.practicaapi.R
import com.moviles.practicaapi.models.Persona
import com.moviles.practicaapi.models.Post
import com.moviles.practicaapi.repositories.PersonaRepository
import com.moviles.practicaapi.repositories.PostRepository
import com.moviles.practicaapi.ui.adapters.PersonaAdapter
import com.moviles.practicaapi.ui.adapters.PostAdapter


class MainActivity : AppCompatActivity(), PostRepository.PostListListener,
    PersonaAdapter.OnItemPersonaClickListener,
    PostRepository.PostByIdListener,
    PersonaRepository.PersonaByIdListener,
    PersonaRepository.PersonaListListener,
    PersonaRepository.PersonaDeleteListener {

    private lateinit var btnCreatePersona: FloatingActionButton
    private lateinit var btnCallApi: Button
    private lateinit var lstPosts: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnCallApi = findViewById(R.id.btnCallApi)
        lstPosts = findViewById(R.id.lstPosts)
        btnCreatePersona = findViewById(R.id.btnCreatePersona)
        setupEventListeners()
    }

    private fun setupEventListeners() {
        btnCallApi.setOnClickListener {
            callPersonaListApi()
        }
        btnCreatePersona.setOnClickListener {
            val intent = Intent(this, FormPersona::class.java)
            intent.putExtra("persona", Persona())
            startActivity(intent)
        }
    }

    private fun callPostListApi() {
        PostRepository.getPosts(this)
    }

    private fun callPersonaListApi() {
        PersonaRepository.getPersonas(this)
    }

    override fun onResume() {
        super.onResume()
        callPersonaListApi()
    }

    override fun onPostListSuccess(posts: List<Post>?) {
        val adapter = PostAdapter(posts as ArrayList<Post>)
        lstPosts.layoutManager =
            LinearLayoutManager(this@MainActivity)
        lstPosts.adapter = adapter
    }

    override fun onPostListFailure(t: Throwable) {
        Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
    }


    private fun callPostApi() {
        PostRepository.getPost(1, this)
    }

    override fun onPostByIdSuccess(post: Post?) {
        Toast.makeText(this@MainActivity, post?.title, Toast.LENGTH_SHORT)
            .show()
    }

    override fun onPostByIdFailure(t: Throwable) {
        Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
    }

    override fun onPersonaByIdSuccess(post: Persona?) {
        Toast.makeText(this@MainActivity, post?.nombres, Toast.LENGTH_SHORT)
            .show()
    }

    override fun onPersonaByIdFailure(t: Throwable) {
        Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
    }

    override fun onPersonaListSuccess(posts: List<Persona>?) {
        val adapter = PersonaAdapter(posts as ArrayList<Persona>, this)
        lstPosts.layoutManager =
            LinearLayoutManager(this@MainActivity)
        lstPosts.adapter = adapter
    }

    override fun onPersonaListFailure(t: Throwable) {
        Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
    }

    override fun onItemPersonaClick(persona: Persona) {
        val intent = Intent(this, FormPersona::class.java)
        intent.putExtra("persona", persona)
        startActivity(intent)
    }

    override fun onDeletePersonaClick(persona: Persona) {
        PersonaRepository.deletePersona(persona.id, this)
        callPersonaListApi()
    }

    override fun onPersonaDeleteSuccess(post: Persona?) {
        Toast.makeText(this@MainActivity, "Persona eliminada", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onPersonaDeleteFailure(t: Throwable) {
        Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
    }
}
package com.moviles.practicaapi.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.moviles.practicaapi.R
import com.moviles.practicaapi.models.Persona
import com.moviles.practicaapi.repositories.PersonaRepository

class FormPersona : AppCompatActivity(), PersonaRepository.PersonaCreateListener,
    PersonaRepository.PersonaUpdateListener {
    private lateinit var txtNombres: EditText
    private lateinit var txtApellidos: EditText
    private lateinit var txtEdad: EditText
    private lateinit var txtCiudad: EditText
    private lateinit var txtFechaNacimiento: EditText
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button
    private var persona: Persona = Persona()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_persona)
        txtNombres = findViewById(R.id.txtNombres)
        txtApellidos = findViewById(R.id.txtApellidos)
        txtEdad = findViewById(R.id.txtEdad)
        txtCiudad = findViewById(R.id.txtCiudad)
        txtFechaNacimiento = findViewById(R.id.txtFechaNacimiento)
        btnSave = findViewById(R.id.btnSave)
        btnCancel = findViewById(R.id.btnCancel)
        persona = intent.getSerializableExtra("persona") as Persona
        if (persona.id > 0) {
            txtNombres.setText(persona.nombres)
            txtApellidos.setText(persona.apellidos)
            txtEdad.setText(persona.edad.toString())
            txtCiudad.setText(persona.ciudad)
            txtFechaNacimiento.setText(persona.fechaNacimiento)
        }
        setupEventListeners()
    }

    private fun setupEventListeners() {
        btnSave.setOnClickListener {
            val nombres = txtNombres.text.toString()
            val apellidos = txtApellidos.text.toString()
            val edad = txtEdad.text.toString().toInt()
            val ciudad = txtCiudad.text.toString()
            val fechaNacimiento = txtFechaNacimiento.text.toString()
            val persona = Persona(
                nombres = nombres,
                apellidos = apellidos,
                edad = edad,
                ciudad = ciudad,
                fechaNacimiento = fechaNacimiento
            )
            if (this.persona.id > 0) {
                PersonaRepository.updatePersona(this.persona.id, persona, this)
            } else {
                PersonaRepository.createPersona(persona, this)
            }
        }
        btnCancel.setOnClickListener {
            finish()
        }
    }

    override fun onPersonaCreateSuccess(post: Persona?) {
        finish()
    }

    override fun onPersonaCreateFailure(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
    }

    override fun onPersonaUpdateSuccess(post: Persona?) {
        finish()
    }

    override fun onPersonaUpdateFailure(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
    }
}
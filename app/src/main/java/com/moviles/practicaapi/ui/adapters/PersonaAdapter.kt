package com.moviles.practicaapi.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moviles.practicaapi.R
import com.moviles.practicaapi.models.Persona

class PersonaAdapter(val data: ArrayList<Persona>, val listener: OnItemPersonaClickListener) :
    RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder>() {
    class PersonaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lblNombres = itemView.findViewById<TextView>(R.id.lblNombres)
        val lblApellidos = itemView.findViewById<TextView>(R.id.lblApellidos)
        val lblEdad = itemView.findViewById<TextView>(R.id.lblEdad)
        val lblCiudad = itemView.findViewById<TextView>(R.id.lblCiudad)
        val lblFechaNacimiento = itemView.findViewById<TextView>(R.id.lblFechaNacimiento)
        val btnDelete = itemView.findViewById<Button>(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.persona_item_layout, parent, false)
        return PersonaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        val persona = data[position]
        holder.lblNombres.text = persona.nombres
        holder.lblApellidos.text = persona.apellidos
        holder.lblEdad.text = persona.edad.toString()
        holder.lblCiudad.text = persona.ciudad
        holder.lblFechaNacimiento.text = persona.fechaNacimiento
        holder.itemView.setOnClickListener {
            listener.onItemPersonaClick(persona)
        }
        holder.btnDelete.setOnClickListener {
            listener.onDeletePersonaClick(persona)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnItemPersonaClickListener {
        fun onItemPersonaClick(persona: Persona)
        fun onDeletePersonaClick(persona: Persona)
    }
}
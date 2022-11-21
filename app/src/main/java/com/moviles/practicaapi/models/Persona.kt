package com.moviles.practicaapi.models

import java.io.Serializable

class Persona(
    var id: Int = 0,
    var nombres: String = "",
    var apellidos: String = "",
    var edad: Int = 0,
    var ciudad: String = "",
    var fechaNacimiento: String = "",
) : Serializable {
    override fun toString(): String {
        return "Persona(id=$id, nombres='$nombres', apellidos='$apellidos', edad=$edad, ciudad='$ciudad', fechaNacimiento='$fechaNacimiento')"
    }
}
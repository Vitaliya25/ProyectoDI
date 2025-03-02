package com.example.composecatalog.model

import android.content.Context
import android.content.SharedPreferences

class PersistUsuario(context: Context) {

    private val pref: SharedPreferences = context.getSharedPreferences("UsuarioPref", Context.MODE_PRIVATE)

    // Guardar los datos del usuario en SharedPreferences
    fun guardarUsuario(usuario: Usuario) {
        pref.edit().apply {
            putString("NombreUsuario", usuario.nombre)
            putString("ContrasenaUsuario", usuario.contrasena)
            putString("Genero", usuario.genero)
            putInt("Edad", usuario.edad)
            putInt("Peso", usuario.peso)
            putInt("Altura", usuario.altura)
            apply()  // Usar apply para guardar los cambios de manera asíncrona
        }
    }

    // Obtener el nombre del usuario desde SharedPreferences
    fun getNombre(): String {
        return pref.getString("NombreUsuario", "") ?: "" // Retorna un valor por defecto vacío si no existe
    }
    fun getContrasena(): String {
        return pref.getString("ContrasenaUsuario", "") ?: ""
    }

    // Verifica si la clave existe y si el valor de "NombreUsuario" no es vacío
    fun usuarioExiste(): Boolean {
        val nombreGuardado = pref.getString("NombreUsuario", "")
        return nombreGuardado?.isNotEmpty() == true
    }


    fun actualizarContrasena(nuevaContrasena: String) {
        pref.edit().apply {
            putString("ContrasenaUsuario", nuevaContrasena)
            apply()  // Usar apply para guardar los cambios de manera asíncrona
        }
    }

    fun obtenerUsuario(): Usuario {
        // Recuperamos los valores guardados en SharedPreferences
        val nombre = pref.getString("NombreUsuario", "") ?: ""
        val contrasena = pref.getString("ContrasenaUsuario", "") ?: ""
        val genero = pref.getString("Genero", "Hombre") ?: "Hombre"
        val edad = pref.getInt("Edad", 0) // Edad como Int
        val peso = pref.getInt("Peso", 0) // Peso como Int
        val altura = pref.getInt("Altura", 0) // Altura como Int

        // Creamos y retornamos el objeto Usuario con los datos obtenidos
        return Usuario(
            nombre = nombre,
            contrasena = contrasena,
            genero = genero,
            edad = edad,
            peso = peso,
            altura = altura
        )
    }



}

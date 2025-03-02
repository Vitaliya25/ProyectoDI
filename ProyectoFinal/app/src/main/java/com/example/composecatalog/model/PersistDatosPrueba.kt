package com.example.composecatalog.model

import android.content.Context
import android.content.SharedPreferences
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object PersistDatosPrueba {

    //private const val PREFERENCES_NAME = "mi_preferencia"
    private const val PREFERENCES_NAME = "mi_datos_pruebas"
    private const val KEY_DATOS_PRUEBA = "datos_prueba"

    // Función para guardar un listado de DatosPrueba en SharedPreferences
    fun saveDatosPrueba(context: Context, listaDatosPrueba: List<DatosPrueba>) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Convertir la lista de DatosPrueba a JSON
        val json = Json.encodeToString(listaDatosPrueba)

        // Guardar el JSON como String en SharedPreferences
        editor.putString(KEY_DATOS_PRUEBA, json)
        editor.apply()
    }

    // Función para obtener un listado de DatosPrueba desde SharedPreferences
    fun getDatosPrueba(context: Context): List<DatosPrueba> {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

        // Obtener el JSON guardado en SharedPreferences
        val json = sharedPreferences.getString(KEY_DATOS_PRUEBA, "[]")

        // Convertir el JSON de nuevo a una lista de DatosPrueba
        return Json.decodeFromString(json ?: "[]")
    }
}

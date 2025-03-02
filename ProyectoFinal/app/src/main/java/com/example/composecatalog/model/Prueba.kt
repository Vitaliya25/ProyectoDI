package com.example.composecatalog.model


import androidx.annotation.DrawableRes
import kotlinx.serialization.Serializable

data class Usuario(
    val nombre: String,
    val contrasena: String,
    val edad: Int,
    val peso: Int,
    val altura: Int,
    val genero: String
)


data class Prueba (
    var nombre: String,
    var tipo: String,
    @DrawableRes var imagen: Int,
    var descripcion: String
)

@Serializable
data class DatosPrueba(
    var nombre:String,
    var resultado: Double,
    var nota: Double?,
    var fecha: String
)

@Serializable
data class Baremo(
    val abdominales: List<Double>,
    val flexibilidad: List<Double>,
    val testCooper: List<Double>,
    val velocidad: List<Double>?,
    val balon: List<Double>?,
    val notas: List<Double>
)

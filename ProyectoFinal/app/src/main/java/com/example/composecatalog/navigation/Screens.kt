package com.example.composecatalog.navigation

import kotlinx.serialization.Serializable

@Serializable
object Inicio

@Serializable
object Login

@Serializable
data class Usuario(val  name:String)

@Serializable
data class Pruebas(val  prueba:String)

@Serializable
data class DatosPrueba(val  enlace:String)
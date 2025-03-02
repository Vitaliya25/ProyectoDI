package com.example.composecatalog.model

import android.util.Log

class Calificador {

    // Función que obtiene la lista de resultados según la edad, el género y el nombre de la prueba
    fun obtenerResultadosPrueba(edad: Int, genero: String, nombrePrueba: String): List<Double> {
        val baremo = when {
            edad >= 16 -> {
                if (genero == "H") BaremoData.Baremo16H else BaremoData.Baremo16M
            }

            edad == 15 -> {
                if (genero == "H") BaremoData.Baremo15H else BaremoData.Baremo15M
            }

            edad == 14 -> {
                if (genero == "H") BaremoData.Baremo14H else BaremoData.Baremo14M
            }

            edad == 13 -> {
                if (genero == "H") BaremoData.Baremo13H else BaremoData.Baremo13M
            }

            edad == 12 -> {
                if (genero == "H") BaremoData.Baremo12H else BaremoData.Baremo12M
            }

            else -> throw IllegalArgumentException("Edad fuera del rango soportado.")
        }

        // Obtener la lista de resultados para la prueba seleccionada
        return when (nombrePrueba) {
            "abdominales" -> baremo.abdominales
            "flexibilidad" -> baremo.flexibilidad
            "testCooper" -> baremo.testCooper
            "velocidad" -> baremo.velocidad
                ?: throw IllegalArgumentException("Prueba de velocidad no disponible para esta edad.")

            "balon" -> baremo.balon
                ?: throw IllegalArgumentException("Prueba de lanzamiento de balón no disponible para esta edad.")

            else -> throw IllegalArgumentException("Prueba no reconocida.")
        }
    }


    // Función que obtiene la nota según la edad, género, resultado y prueba
    fun obtenerNota(edad: Int, genero: String, resultado: Double, nombrePrueba: String): Double? {
        // Se selecciona el baremo según la edad y el género
        val baremo: Baremo = when {
            edad >= 16 -> {
                if (genero == "H") BaremoData.Baremo16H else BaremoData.Baremo16M
            }

            edad == 15 -> {
                if (genero == "H") BaremoData.Baremo15H else BaremoData.Baremo15M
            }

            edad == 14 -> {
                if (genero == "H") BaremoData.Baremo14H else BaremoData.Baremo14M
            }

            edad == 13 -> {
                if (genero == "H") BaremoData.Baremo13H else BaremoData.Baremo13M
            }

            edad == 12 -> {
                if (genero == "H") BaremoData.Baremo12H else BaremoData.Baremo12M
            }

            else -> {
                throw IllegalArgumentException("Edad fuera del rango soportado.")
            }
        }

        // Obtener la lista de resultados según el nombre de la prueba
        val resultadosPrueba: List<Double> = when (nombrePrueba) {
            "abdominales" -> baremo.abdominales
            "flexibilidad" -> baremo.flexibilidad
            "testCooper" -> baremo.testCooper
            "velocidad" -> baremo.velocidad
                ?: throw IllegalArgumentException("Prueba de velocidad no disponible para esta edad.")

            "balon" -> baremo.balon
                ?: throw IllegalArgumentException("Prueba de lanzamiento de balón no disponible para esta edad.")

            else -> throw IllegalArgumentException("Prueba no reconocida.")
        }

        // Verificar si el resultado está en la lista de resultados
        Log.d(
            "Calificador",
            "Resultados de la prueba para $nombrePrueba: ${resultadosPrueba.joinToString()}"
        )


        if (nombrePrueba.equals("velocidad")) {
            // Buscar el índice correspondiente al resultado y devolver la nota
            for (i in resultadosPrueba.indices) {
                Log.d(
                    "Calificador",
                    "Comprobando si el resultado ($resultado) es menor o igual que ${resultadosPrueba[i]}"
                )
                if (resultado >= resultadosPrueba[i]) {
                    Log.d("Calificador", "Nota calculada: ${baremo.notas[i]}")
                    return baremo.notas[i]
                }
            }
        } else {

            for (i in resultadosPrueba.indices) {
                Log.d(
                    "Calificador",
                    "Comprobando si el resultado ($resultado) es menor o igual que ${resultadosPrueba[i]}"
                )
                if (resultado <= resultadosPrueba[i]) {
                    Log.d("Calificador", "Nota calculada: ${baremo.notas[i]}")
                    return baremo.notas[i]
                }
            }
        }


        // Si no se encontró un resultado válido, se puede lanzar una excepción o retornar null
        return baremo.notas.last() // Devuelve la nota más alta si el resultado es el mejor
    }

}
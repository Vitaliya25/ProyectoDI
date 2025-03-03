package com.example.composecatalog.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ThemeViewModel : ViewModel() {
    // Variable para almacenar si el modo oscuro está activado o no
    var isDarkMode by mutableStateOf(false)

    // Función para alternar entre el modo claro y oscuro
    fun toggleDarkMode() {
        isDarkMode = !isDarkMode
    }
}
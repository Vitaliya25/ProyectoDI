package com.example.composecatalog.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


val lightColors = lightColorScheme(
    primary = Color(0xFF6200EE),  // Aquí define tu color primario para el modo claro
    onPrimary = Color.White,
    background = Color.White,
    surface = Color.White,
    onSurface = Color.Black
)

val darkColors = darkColorScheme(
    primary = Color(0xFF90CAF9),  // Aquí define tu color primario para el modo oscuro
    onPrimary = Color.Black,
    background = Color.Black,
    surface = Color.Black,
    onSurface = Color.White
)
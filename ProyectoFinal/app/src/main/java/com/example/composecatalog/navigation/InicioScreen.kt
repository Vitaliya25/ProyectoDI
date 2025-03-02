package com.example.composecatalog.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecatalog.R

@Composable
fun Inicio(navigateToLogin: () -> Unit) {
    // Se crea una columna que contiene todos los elementos de la pantalla.
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "EuroFit", fontSize = 90.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.azulTexto)
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.titulo),
            contentDescription = "Imagen de Ejemplo",
            modifier = Modifier.padding(8.dp),
            alpha = 0.9f
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { navigateToLogin() },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.azulTexto),
            ),

            modifier = Modifier.size(250.dp,70.dp)
        ) {
            Text(text = "EMPEZAMOS",
                fontSize = 20.sp)

        }
        Spacer(modifier = Modifier.weight(1f))
    }
}
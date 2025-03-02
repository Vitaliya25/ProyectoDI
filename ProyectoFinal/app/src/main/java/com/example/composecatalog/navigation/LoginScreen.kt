package com.example.composecatalog.navigation

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecatalog.R
import com.example.composecatalog.R.color.azulTexto
import com.example.composecatalog.model.PersistUsuario
import com.example.composecatalog.model.Usuario

@Composable
fun LoginScreen(navigateToUsuario: (String) -> Unit, navigateBack: () -> Unit) {

    val context = LocalContext.current
    var pUsuario: PersistUsuario = remember { PersistUsuario(context) }
    var nombreUsuario by remember { mutableStateOf(pUsuario.getNombre()) }
    var contrasena by remember { mutableStateOf("") }
    var restablecerContrasena by remember { mutableStateOf(false) }
    var nuevaContrasena by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var usuario = pUsuario.obtenerUsuario()

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "INICIO DE SESION", fontSize = 25.sp, fontWeight = FontWeight.Bold,
            color = colorResource(R.color.azulTexto)
        )

        Spacer(modifier = Modifier.weight(1f))
        Text(text = "USUARIO", fontSize = 20.sp)
        TextField(
            value = nombreUsuario,
            onValueChange = { nombreUsuario = it },
            singleLine = true
        )

        Spacer(modifier = Modifier.weight(1f))
        Text(text = "CONTRASEÑA", fontSize = 20.sp)
        TextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        // Mostrar mensaje de error si la contraseña es incorrecta
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        // TextButton para "¿Has olvidado la contraseña?"
        TextButton(onClick = { restablecerContrasena = true }) {
            Text(
                text = "¿Has olvidado la contraseña?",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

// Mostrar Card para restablecer la contraseña si se presiona el TextButton
        if (restablecerContrasena) {
            Card(
                modifier = Modifier.padding(16.dp),
                elevation = CardDefaults.cardElevation(),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Restablecer contraseña",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // TextField para introducir la nueva contraseña
                    TextField(
                        value = nuevaContrasena,
                        onValueChange = { nuevaContrasena = it },
                        label = { Text("Nueva Contraseña") },
                        singleLine = true,
                        //visualTransformation = PasswordVisualTransformation() // Ocultar la nueva contraseña
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            // Guardar la nueva contraseña en SharedPreferences
                            pUsuario.guardarUsuario(
                                Usuario(
                                    nombre = nombreUsuario,
                                    contrasena = nuevaContrasena,
                                    genero = usuario.genero,
                                    edad = usuario.edad,
                                    peso = usuario.peso,
                                    altura = usuario.altura
                                )
                            )
                            restablecerContrasena = false // Cerrar el card después de guardar


                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.azulTexto),
                        ),
                        modifier = Modifier.size(150.dp, 50.dp)
                    ) {
                        Text(text = "Guardar", fontSize = 16.sp)
                    }
                }
            }
        }


        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                // Verificar si el nombre de usuario existe en SharedPreferences
                if (!pUsuario.usuarioExiste()) {
                    // Crear un nuevo usuario si no existe
                    val usuario = Usuario(
                        nombre = nombreUsuario,
                        contrasena = contrasena,
                        genero = "Desconocido", // Valor por defecto
                        edad = 0, // Valor por defecto
                        peso = 0, // Valor por defecto
                        altura = 0 // Valor por defecto
                    )
                    pUsuario.guardarUsuario(usuario) // Guardar el nuevo usuario
                    navigateToUsuario(nombreUsuario) // Navegar si el usuario se crea
                } else {
                    // Si el usuario existe, verificar la contraseña
                    val contrasenaGuardada = pUsuario.getContrasena()
                    if (contrasena == contrasenaGuardada) {
                        navigateToUsuario(nombreUsuario) // Navegar si la contraseña es correcta
                    } else {
                        errorMessage = "La contraseña es incorrecta"
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.azulTexto),
            ),
            modifier = Modifier
                .size(200.dp, 50.dp)
                .clip(RoundedCornerShape(3.dp))
        ) {
            Text(
                text = "ACCEDER",
                fontSize = 20.sp
            )

        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navigateBack() },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(azulTexto)
            ),
            modifier = Modifier
                .size(150.dp, 50.dp)
        )
        {
            Text(
                text = "VOLVER",
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.weight(1f))

    }
}
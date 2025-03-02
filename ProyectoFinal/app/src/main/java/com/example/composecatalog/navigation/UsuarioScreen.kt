package com.example.composecatalog.navigation

import android.widget.Toast
import androidx.compose.foundation.clickable

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.composecatalog.R
import com.example.composecatalog.R.color.azulTexto
import com.example.composecatalog.model.PersistDatosPrueba
import com.example.composecatalog.model.PersistUsuario
import com.example.composecatalog.model.Usuario

@Composable
fun UsuarioScreen(nombre: String, navigateToPruebas: (String) -> Unit, navigateBack: () -> Unit) {
    var edad by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var sexo by remember { mutableStateOf("H") }
    var imc by remember { mutableStateOf("") }
    var mostrarDialogo by remember { mutableStateOf(false) }
    var mostrarDialogoPruebas by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val pUsuario = remember { PersistUsuario(context) }
    val usuario = pUsuario.obtenerUsuario()
    var pruebas = PersistDatosPrueba.getDatosPrueba(context)
    println(pruebas.toString())
    // Si hay un usuario guardado, asigna sus valores a las variables de estado
    LaunchedEffect(usuario) {
        if (usuario != null) {
            edad = usuario.edad.toString()
            peso = usuario.peso.toString()
            altura = usuario.altura.toString()
            sexo = usuario.genero
        }
    }

    fun calcularIMC() {
        if (peso.isNotEmpty() && altura.isNotEmpty()) {
            try {
                val pesoDouble = peso.toDouble()
                val alturaDouble = altura.toDouble()

                if (pesoDouble != null && alturaDouble != null && alturaDouble > 0) {
                    val resultadoIMC = (pesoDouble / (alturaDouble * alturaDouble)) * 10000
                    imc = String.format("%.2f", resultadoIMC)
                } else {
                    imc = "Datos invalidos"
                }
            } catch (e: Exception) {
                imc = ""
            }
        } else {
            imc = "Ingrese peso y altura"
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "USUARIO:  $nombre", fontSize = 25.sp, fontWeight = FontWeight.Bold,
            color = colorResource(azulTexto)
        )


        Spacer(modifier = Modifier.weight(1f))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 3.dp)
        ) {
            Text(
                text = "EDAD", fontSize = 20.sp,
                color = colorResource(azulTexto),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(8.dp))
            TextField(
                value = edad, onValueChange = { edad = it },
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 3.dp)

        ) {
            Text(
                text = "PESO", fontSize = 20.sp,
                color = colorResource(azulTexto),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(8.dp))
            TextField(
                value = peso, onValueChange = { peso = it },
                singleLine = true
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 3.dp)
        ) {
            Text(
                text = "ALTURA", fontSize = 20.sp,
                color = colorResource(azulTexto),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(8.dp))
            TextField(
                value = altura, onValueChange = { altura = it },
                singleLine = true
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 3.dp)
        ) {
            Text(
                text = "SEXO", fontSize = 20.sp,
                color = colorResource(azulTexto),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(8.dp))
            RadioButton(
                selected = (sexo == "H"), onClick = { sexo = "H" },
                enabled = true
            )
            Text(
                text = "Hombre"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RadioButton(
                selected = (sexo == "M"), onClick = { sexo = "M" }, enabled = true
            )
            Text(
                text = "Mujer"
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                modifier = Modifier.clickable {
                    mostrarDialogoPruebas = true
                },
                text = "Mostrar pruebas realizadas", fontSize = 20.sp,
                color = colorResource(R.color.teal_700),
                fontWeight = FontWeight.Bold,
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
        {
            Button(
                onClick = { navigateToPruebas(edad) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(azulTexto),
                ),
                modifier = Modifier
                    .size(150.dp, 50.dp)
                    .align(Alignment.CenterEnd)
            ) {
                Text(
                    text = "PRUEBAS",
                    fontSize = 20.sp
                )
            }

            Button(
                onClick = {
                    calcularIMC()
                    mostrarDialogo = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(azulTexto),
                ),
                modifier = Modifier
                    .size(150.dp, 50.dp)
                    .align(Alignment.CenterStart)
            ) {
                Text(
                    text = "IMC",
                    fontSize = 20.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
        {
            Button(
                onClick = { navigateBack() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(azulTexto),
                ),
                modifier = Modifier
                    .size(150.dp, 50.dp)
                    .align(Alignment.CenterStart)
            )
            {
                Text(
                    text = "VOLVER",
                    fontSize = 20.sp
                )

            }

            Button(
                onClick = {
                    // Guardar los datos en SharedPreferences cuando el botón se presiona
                    val usuarioN = Usuario(
                        nombre = nombre,
                        contrasena = usuario.contrasena,
                        genero = sexo,
                        edad = edad.toInt(),
                        peso = peso.toInt(),
                        altura = altura.toInt()
                    )
                    pUsuario.guardarUsuario(usuarioN)
                    Toast.makeText(context, "Datos de usuario guardados", Toast.LENGTH_SHORT).show()

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(azulTexto),
                ),
                modifier = Modifier
                    .size(150.dp, 50.dp)
                    .align(Alignment.TopEnd)
            ) {
                Text(
                    text = "GUARDAR",
                    fontSize = 20.sp
                )
            }

        }
        Spacer(modifier = Modifier.weight(1f))
    }



    if (mostrarDialogo) {
        pruebas = PersistDatosPrueba.getDatosPrueba(context)
        AlertDialog(modifier = Modifier
            .padding(3.dp)
            .width(300.dp)
            .height(200.dp),
            containerColor = colorResource(R.color.azulFondo),
            onDismissRequest = {
                mostrarDialogo = false
            },
            title = {
                Text(
                    text = "RESULTADO DEL IMC",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = "$imc",
                    fontSize = 24.sp
                )
            },
            confirmButton = {
                Button(
                    onClick = { mostrarDialogo = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(azulTexto)
                    )
                )
                {
                    Text(
                        text = "Cerrar",
                        fontSize = 20.sp
                    )
                }
            }
        )
    }

    if (mostrarDialogoPruebas) {
        AlertDialog(
            modifier = Modifier.fillMaxWidth()
                .padding(3.dp) ,
            // Ajusta el tamaño según sea necesario
            containerColor = colorResource(R.color.azulFondo),
            onDismissRequest = {
                mostrarDialogoPruebas = false  // Cerrar el diálogo cuando se toque fuera
            },
            title = {
                Text(
                    text = "ULTIMAS PRUEBAS REALIZADAS:",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .verticalScroll(rememberScrollState())  // Hacemos que el contenido sea desplazable
                ) {
                    pruebas.forEach { prueba ->
                        // Mostrar el nombre de la prueba
                        Text(
                            text = "Prueba: ${prueba.nombre}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        // Mostrar el resultado
                        Text(
                            text = "Resultado: ${prueba.resultado}",
                            fontSize = 18.sp
                        )

                        // Mostrar la nota (si está disponible)
                        Text(
                            text = "Nota: ${prueba.nota?.toString() ?: "N/A"}",  // Si la nota es nula, mostrar "N/A"
                            fontSize = 18.sp
                        )

                        // Verificar si la fecha está vacía y mostrar un mensaje adecuado
                        val fechaTexto =
                            if (prueba.fecha.isNotEmpty()) prueba.fecha else "Fecha no disponible"
                        Text(
                            text = "Fecha: $fechaTexto",
                            fontSize = 18.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp)) // Espaciado entre las pruebas
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { mostrarDialogoPruebas = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.azulTexto)
                    ),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = "Cerrar",
                        fontSize = 20.sp
                    )
                }
            }
        )
    }
}



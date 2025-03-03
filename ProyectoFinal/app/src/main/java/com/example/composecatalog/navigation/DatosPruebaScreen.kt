package com.example.composecatalog.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecatalog.R
import com.example.composecatalog.model.Calificador
import com.example.composecatalog.model.DatosPrueba
import com.example.composecatalog.model.PersistDatosPrueba
import com.example.composecatalog.model.PersistUsuario
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Locale


@Composable
fun DatosPruebaScreen(nombre: String, navigateBack: () -> Unit) {

    var context = LocalContext.current
    // Recuperamos los datos del usuario desde el almacenamiento persistente
    var usuario = PersistUsuario(context).obtenerUsuario()
    //Variables para almacenar los valores anteriores y actuales de la prueba
    var resultadoAnt by remember { mutableStateOf("") }
    var notaAnt by remember { mutableStateOf("") }
    var fechaAnt by remember { mutableStateOf("") }
    var resultadoAct by remember { mutableStateOf("") }

    var notaAct by remember { mutableStateOf("") }

    val calificador = Calificador()
    // Recuperamos la lista de datos de pruebas guardados
    val listaDeDatosPrueba = PersistDatosPrueba.getDatosPrueba(context)
    // Buscamos los datos de la prueba actual usando el nombre
    val datosPrueba = listaDeDatosPrueba.find { it.nombre == nombre }
        ?: DatosPrueba(nombre = nombre, resultado = 0.0, nota = 0.0, fecha = "")

    // Asignamos los valores iniciales de la prueba (si existen)
    resultadoAnt = datosPrueba.resultado.toString()
    notaAnt = datosPrueba.nota.toString()
    fechaAnt = datosPrueba.fecha

    // Obtenemos los resultados de la prueba usando los datos del usuario
    val resultadosPrueba = calificador.obtenerResultadosPrueba(usuario.edad, usuario.genero, nombre)

    // Función para calcular la nueva nota según el resultado introducido
    fun calcularNuevaNota() {
        // Primero, calculamos la nueva nota usando el calificador
        val resultadoDouble =
            resultadoAct.toDoubleOrNull() ?: 0.0 // Convertir el resultado a Double
        val nuevaNota =
            calificador.obtenerNota(usuario.edad, usuario.genero, resultadoDouble, nombre)

        // Si obtenemos una nota válida, la asignamos a notaAct
        if (nuevaNota != null) {
            notaAct = nuevaNota.toString()
        } else {
            notaAct =
                "0" // Si la nota es nula, asignamos un valor predeterminado (puedes manejarlo de otra manera)
        }
    }


    // Función para guardar el nuevo resultado
    fun guardarNuevoResultado() {
        calcularNuevaNota()
        val calendar = Calendar.getInstance()
        val fechaActual = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time)

        // Actualizamos el objeto DatosPrueba con el nuevo resultado y nota
        val nuevoResultado = DatosPrueba(
            nombre = nombre,
            resultado = resultadoAct.toDoubleOrNull() ?: 0.0,  // Usamos el nuevo resultado
            nota = notaAct.toDoubleOrNull() ?: 0.0,  // Usamos la nueva nota calculada
            fecha = fechaActual
        )

        // Actualizamos la lista de datos, reemplazando el que tiene el mismo nombre
        val listaActualizada = listaDeDatosPrueba.toMutableList().apply {
            val index = indexOfFirst { it.nombre == nombre }
            if (index >= 0) {
                this[index] = nuevoResultado  // Reemplazar
            } else {
                this.add(nuevoResultado)      // Agregar si no existía
            }
        }

        // Guardar la lista actualizada en SharedPreferences
        PersistDatosPrueba.saveDatosPrueba(context, listaActualizada)
        notaAnt = notaAct
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),

        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "PRUEBA DE  $nombre: ",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = colorResource(R.color.azulTexto),
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "RESULTADO:      $resultadoAnt ",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = colorResource(R.color.azulTextoOscuro),
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "NOTA:       $notaAnt",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = colorResource(R.color.azulTextoOscuro),
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "FECHA:      $fechaAnt",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = colorResource(R.color.azulTextoOscuro),
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "NUEVO RESULTADO:",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            color = colorResource(R.color.azulTextoOscuro),
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))
        TextField(value = resultadoAct, maxLines = 1, onValueChange = { resultadoAct = it })

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "NUEVA NOTA:     $notaAct",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            color = colorResource(R.color.azulTextoOscuro),
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start),
        ) {

            Button(
                onClick = {
                    calcularNuevaNota()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.azulTexto),
                ),
                modifier = Modifier
                    .size(150.dp, 50.dp)
                    .clip(RoundedCornerShape(3.dp))
            ) {
                Text(
                    text = "CALCULAR",
                    fontSize = 20.sp
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {

            Button(
                onClick = { navigateBack() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.azulTexto),
                ),
                modifier = Modifier
                    .size(150.dp, 50.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .align(Alignment.CenterStart)
            ) {
                Text(
                    text = "VOLVER",
                    fontSize = 20.sp
                )

            }

            Button(
                onClick = {
                    guardarNuevoResultado()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.azulTexto),
                ),
                modifier = Modifier
                    .size(150.dp, 50.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .align(Alignment.CenterEnd)
            ) {
                Text(
                    text = "GUARDAR",
                    fontSize = 20.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
    }


}

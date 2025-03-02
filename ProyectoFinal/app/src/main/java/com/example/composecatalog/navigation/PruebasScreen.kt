package com.example.composecatalog.navigation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecatalog.R
import com.example.composecatalog.model.Prueba
import kotlinx.coroutines.launch

@Composable
fun PruebasScreen(
    prueba: String,
    navigateToDatosPrueba: (String) -> Unit,
    navigateBack: () -> Unit
) {
    val edad = prueba.toIntOrNull() ?: 0
// Estado para manejar el tipo de prueba seleccionado y la visibilidad del menú desplegable.
    var tipoSeleccionado by remember { mutableStateOf("Todas") }
    var expandido by remember { mutableStateOf(false) }
    // Lista de tipos de prueba que se pueden seleccionar.
    val tipos = listOf("Todas", "Resistencia", "Flexibilidad", "Fuerza muscular", "Velocidad")


    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "PRUEBAS", fontSize = 25.sp, fontWeight = FontWeight.Bold,
            color = colorResource(R.color.azulTexto)
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Caja de texto con un menú desplegable para seleccionar el tipo de prueba
        Box(
            modifier = Modifier.fillMaxWidth()
                .weight(1f)
        ) {
            OutlinedTextField(
                value = tipoSeleccionado,
                onValueChange = { tipoSeleccionado = it },
                enabled = true,
                readOnly = false,
                modifier = Modifier
                    .clickable { expandido = true }
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                textStyle = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Normal,
                    color = colorResource(R.color.azulTextoOscuro)
                )
            )
            // Menú desplegable que muestra los tipos de prueba
            DropdownMenu(
                expanded = expandido,
                onDismissRequest = { expandido = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                tipos.forEach { tipo ->
                    DropdownMenuItem(text = { Text(text = tipo,
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    ) },
                        onClick = {
                            expandido = false
                            tipoSeleccionado = tipo
                        }
                    )
                }
            }
        }
        // Sección para mostrar las pruebas filtradas según el tipo seleccionado
        Row(modifier = Modifier.weight(12f))
        {
            ListarPruebas(edad, tipoSeleccionado, navigateToDatosPrueba);
        }

        Spacer(modifier = Modifier.weight(1f))
        Row(modifier = Modifier.weight(1f)) {
            Button(
                onClick = { navigateBack() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.azulTexto),
                ),
                modifier = Modifier
                    .size(150.dp, 50.dp)
            ) {
                Text(
                    text = "VOLVER",
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun PruebaItem(prueba: Prueba, onItemSelected: (String) -> Unit) {
    val uriHandler = LocalUriHandler.current
    // Card que muestra la prueba con imagen y nombre
    Card(
        border = BorderStroke(2.dp, color = Color.Gray),
        modifier = Modifier
            .width(380.dp)
            .height(270.dp)
            .clickable { onItemSelected(prueba.nombre) })
    {
        Column() {
            Image(
                painter = painterResource(id = prueba.imagen),
                contentDescription = "Imagen de prueba",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .weight(4f),  // Establecer altura máxima
                contentScale = ContentScale.Crop
            )
            Text(
                text = prueba.nombre.toUpperCase(),
                modifier = Modifier
                    .align(Alignment.Start)
                    .weight(1f)
                    .padding(8.dp),
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.azulTextoOscuro)
            )
            Text(
                text = "Conoce más ...",
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp)
                    .weight(1f)
                    .clickable {
                        uriHandler.openUri(prueba.descripcion)
                    },

                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.azulTexto),
                fontSize = 24.sp
            )
        }
    }
}

@Composable
fun ListarPruebas(edad: Int, tipo: String, navigateToDatosPrueba: (String) -> Unit) {
    val rvState = rememberLazyListState()
    val corutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    // Determina cuántas pruebas mostrar según la edad
    val pruebasAmostrar = when {
        edad < 14 -> getPruebas().take(3)  // Mostrar solo las primeras 3 pruebas
        edad == 14 -> getPruebas().take(4)  // Mostrar las primeras 4 pruebas
        else -> getPruebas()  // Mostrar todas las pruebas
    }

    // Filtrar las pruebas según el tipo de prueba seleccionado
    val pruebasFiltradas = if (tipo == "Todas") {
        pruebasAmostrar
    } else {
        pruebasAmostrar.filter { it.tipo == tipo }
    }

    Column(){
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            state = rvState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            //modifier = Modifier.weight(1f)
        ) {
            items(pruebasFiltradas) { prueba ->
                PruebaItem(prueba = prueba, onItemSelected = { enlace ->
                    navigateToDatosPrueba(enlace)
                })

            }
        }
        val showButton by remember {
            derivedStateOf {
                rvState.firstVisibleItemIndex > 0
            }
        }
        if (showButton) {
            Button(
                onClick = {
                    corutineScope.launch {
                        rvState.animateScrollToItem(0)
                    }
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(16.dp)
            ) {
                Text(text = "Al primero")
            }
        }
    }
}

fun getPruebas(): List<Prueba> {
    // Lista de pruebas predefinidas con su nombre, tipo, imagen y enlace
    return listOf(
        Prueba("testCooper", "Resistencia", R.drawable.test_cooper, "https://www.palabraderunner.com/test-de-cooper/"),
        Prueba("flexibilidad", "Flexibilidad", R.drawable.flexibilidad, "https://www.naradigital.es/blog/detalle-noticias/3005/como-preparar-el-test-de-flexibilidad"),
        Prueba("abdominales", "Fuerza muscular", R.drawable.abdominal, "https://altorendimiento.com/prueba-de-abdominales/?srsltid=AfmBOorS_9S_7NRBGSR2h_IT3MlN2WSrWkG-QTW10Sp9nwPalim3E8zv"),
        Prueba("velocidad", "Velocidad", R.drawable.velocidad, "http://cdeporte.rediris.es/revista/revista13/velocidad.htm"),
        Prueba("balon", "Fuerza muscular", R.drawable.balon, "https://efisicas.com/tutorial-balon-medicinal-oposiciones/"),
    )
}

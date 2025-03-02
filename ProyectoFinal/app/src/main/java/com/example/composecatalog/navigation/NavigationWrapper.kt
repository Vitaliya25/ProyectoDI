package com.example.composecatalog.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlin.reflect.typeOf

@Composable

fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Inicio) {
        composable<Inicio> {
            Inicio { navController.navigate(Login) }
        }
        composable<Login> {
            LoginScreen({ name -> navController.navigate(Usuario(name = name)) },
                { navController.popBackStack() })
        }
        composable<Usuario> { backStackEntry ->
            val usuario = backStackEntry.toRoute<Usuario>()
            UsuarioScreen(usuario.name,
                { prueba -> navController.navigate(Pruebas(prueba = prueba)) },
                { navController.popBackStack() })
        }
        composable<Pruebas> { backStackEntry ->
            val prueba = backStackEntry.toRoute<Pruebas>()
            PruebasScreen(prueba.prueba,
                { enlace -> navController.navigate(DatosPrueba(enlace = enlace)) },
                { navController.popBackStack() })
        }
        composable<DatosPrueba> { backStackEntry ->
            val datosPrueba = backStackEntry.toRoute<DatosPrueba>()
            DatosPruebaScreen(datosPrueba.enlace) { navController.popBackStack() }
        }
    }
}
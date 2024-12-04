package com.example.ejercicio3_pruebaprogramacionandroid2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ejercicio3_pruebaprogramacionandroid2.screens.ListaFarmaciasScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "listaFarmacias") {
        // Pantalla principal: Lista de farmacias
        composable("listaFarmacias") {
            ListaFarmaciasScreen { farmacia ->
                // Aquí podrás navegar al mapa en el futuro
                // navController.navigate("mapaFarmacia/${farmacia.nombre}/${farmacia.telefono}/${farmacia.latitud}/${farmacia.longitud}")
            }
        }
    }
}

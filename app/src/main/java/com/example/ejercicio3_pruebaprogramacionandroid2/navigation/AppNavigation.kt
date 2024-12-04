package com.example.ejercicio3_pruebaprogramacionandroid2.navigation

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ejercicio3_pruebaprogramacionandroid2.activity.MapaActivity
import com.example.ejercicio3_pruebaprogramacionandroid2.screens.ListaFarmaciasScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = "listaFarmacias") {
        composable("listaFarmacias") {
            ListaFarmaciasScreen { farmacia ->
                // Navegar al MapaActivity al seleccionar una farmacia
                val intent = Intent(context, com.example.ejercicio3_pruebaprogramacionandroid2.activity.MapaActivity::class.java).apply {
                    putExtra("nombre", farmacia.nombre)
                    putExtra("latitud", farmacia.latitud)
                    putExtra("longitud", farmacia.longitud)
                }
                context.startActivity(intent)
            }
        }
    }
}

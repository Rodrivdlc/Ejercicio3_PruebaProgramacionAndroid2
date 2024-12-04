package com.example.ejercicio3_pruebaprogramacionandroid2.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.ejercicio3_pruebaprogramacionandroid2.activity.MapaActivity
import com.example.ejercicio3_pruebaprogramacionandroid2.data.Farmacia
import com.example.ejercicio3_pruebaprogramacionandroid2.utils.getFarmaciasFromJson
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch

@Composable
fun ListaFarmaciasScreen(onFarmaciaClick: (Farmacia) -> Unit = {}) {
    val farmacias = remember { mutableStateListOf<Farmacia>() }
    val context = LocalContext.current
    val firebaseDatabase = FirebaseDatabase.getInstance().reference.child("farmacias")

    // Obtener las farmacias
    LaunchedEffect(Unit) {
        val data = getFarmaciasFromJson()

        // Guardar las farmacias en Firebase
        data.forEach { farmacia ->
            firebaseDatabase.push().setValue(farmacia)
        }

        // Actualizar la lista local
        farmacias.addAll(data)
    }

    // Mostrar la lista
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(farmacias) { farmacia ->
            FarmaciaItem(farmacia) {
                // Navegar a MapaActivity
                navigateToMapaActivity(context, farmacia)
            }
        }
    }
}

fun navigateToMapaActivity(context: Context, farmacia: Farmacia) {
    val intent = Intent(context, MapaActivity::class.java).apply {
        putExtra("nombre", farmacia.nombre)
        putExtra("telefono", farmacia.telefono)
        putExtra("latitud", farmacia.latitud)
        putExtra("longitud", farmacia.longitud)
    }
    context.startActivity(intent)
}

@Composable
fun FarmaciaItem(farmacia: Farmacia, onClick: (Farmacia) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick(farmacia) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.primary)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = farmacia.nombre, style = MaterialTheme.typography.bodyLarge)
                Text(text = farmacia.telefono, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

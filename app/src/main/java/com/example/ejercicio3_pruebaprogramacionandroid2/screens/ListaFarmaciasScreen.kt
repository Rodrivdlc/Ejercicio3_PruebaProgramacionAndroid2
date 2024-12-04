package com.example.ejercicio3_pruebaprogramacionandroid2.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ejercicio3_pruebaprogramacionandroid2.data.Farmacia
import com.example.ejercicio3_pruebaprogramacionandroid2.utils.getFarmaciasFromJson
import kotlinx.coroutines.launch

@Composable
fun ListaFarmaciasScreen(onFarmaciaClick: (Farmacia) -> Unit) {
    val farmacias = remember { mutableStateListOf<Farmacia>() }

    // Obtener las farmacias
    LaunchedEffect(Unit) {
        val data = getFarmaciasFromJson()
        farmacias.addAll(data)
    }

    // Mostrar la lista
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(farmacias) { farmacia ->
            FarmaciaItem(farmacia, onFarmaciaClick)
        }
    }
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
            // Icono genérico
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.primary)
            )
            Spacer(modifier = Modifier.width(16.dp))

            // Información de la farmacia
            Column {
                Text(text = farmacia.nombre, style = MaterialTheme.typography.bodyLarge)
                Text(text = farmacia.telefono, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

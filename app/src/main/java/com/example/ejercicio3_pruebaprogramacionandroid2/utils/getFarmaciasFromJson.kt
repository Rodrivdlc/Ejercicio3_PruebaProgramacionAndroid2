package com.example.ejercicio3_pruebaprogramacionandroid2.utils

import com.example.ejercicio3_pruebaprogramacionandroid2.data.Farmacia
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

suspend fun getFarmaciasFromJson(): List<Farmacia> {
    return withContext(Dispatchers.IO) {
        try {
            val url = "https://www.zaragoza.es/georref/json/hilo/farmacias_Equipamiento"
            val json = URL(url).readText()
            val jsonObject = JSONObject(json)
            val featuresArray = jsonObject.getJSONArray("features")
            val farmacias = mutableListOf<Farmacia>()

            for (i in 0 until featuresArray.length()) {
                val feature = featuresArray.getJSONObject(i)
                val geometry = feature.getJSONObject("geometry")
                val properties = feature.getJSONObject("properties")

                // Extraer coordenadas y datos
                val coordinates = geometry.getJSONArray("coordinates")
                val nombre = properties.getString("title")
                val descripcion = properties.getString("description")
                val telefono = descripcion.substringAfter("Teléfono: ").substringBefore(" ") // Extraer teléfono
                val latitud = coordinates.getDouble(1) // Coordenada Y
                val longitud = coordinates.getDouble(0) // Coordenada X

                farmacias.add(Farmacia(nombre, telefono, latitud, longitud))
            }

            farmacias
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}

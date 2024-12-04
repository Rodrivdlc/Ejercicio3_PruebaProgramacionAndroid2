package com.example.ejercicio3_pruebaprogramacionandroid2.activity

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.ejercicio3_pruebaprogramacionandroid2.R
import android.widget.TextView

class MapaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)

        // Recuperar datos del Intent
        val nombre = intent.getStringExtra("nombre") ?: "Farmacia"
        val telefono = intent.getStringExtra("telefono") ?: "Teléfono no disponible"
        val latitud = intent.getDoubleExtra("latitud", 0.0)
        val longitud = intent.getDoubleExtra("longitud", 0.0)

        // Configurar el TextView con la información de la farmacia
        val farmaciaInfoTextView = findViewById<TextView>(R.id.farmacia_info)
        farmaciaInfoTextView.text = """
            Nombre: $nombre
            Teléfono: $telefono
            Latitud: $latitud
            Longitud: $longitud
        """.trimIndent()

        // Configurar la imagen del mapa simulado (opcional, ya está en el diseño XML)
        val simulatedMapImageView = findViewById<ImageView>(R.id.simulated_map)
        simulatedMapImageView.setImageResource(R.drawable.simulated_map) // Asegúrate de tener esta imagen
    }
}
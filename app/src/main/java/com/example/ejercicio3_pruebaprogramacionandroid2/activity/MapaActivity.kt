package com.example.ejercicio3_pruebaprogramacionandroid2.activity

import android.os.Bundle
import android.util.Log
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

        // Mostrar información de la farmacia
        val farmaciaInfoTextView = findViewById<TextView>(R.id.farmacia_info)
        farmaciaInfoTextView.text = "Nombre: $nombre\nTeléfono: $telefono"
    }
}
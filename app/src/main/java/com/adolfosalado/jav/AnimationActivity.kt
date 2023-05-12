package com.adolfosalado.jav

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class AnimationActivity : AppCompatActivity() {
    private val TIMEOUT_DURATION = 5000L // Duración del timeout en milisegundos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)


        val handler = Handler()
        handler.postDelayed({
            // Crear un Intent para iniciar la nueva Activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Opcional: finalizar la Activity actual después de iniciar la nueva Activity
        }, TIMEOUT_DURATION)
    }
}
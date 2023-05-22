package com.adolfosalado.jav

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adolfosalado.jav.databinding.ActivityInicioBinding
import com.adolfosalado.jav.utils.Preferences

class InicioActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInicioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        binding.tvPresentation.text = "¡Bienvenid@! \n" +
                "Realiza las lecciones para adentrarte en el fantástico mundo de Jav: La elegida del Reino Tiku \n" +
                "A continuación, enfréntate a los ejercicios para conseguir las herramientas que necesitan Jav y Titi para vencer a Nully \n" +
                "¿Estás preparad@?"

        binding.btnGoToMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }


}
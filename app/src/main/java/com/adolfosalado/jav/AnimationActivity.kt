package com.adolfosalado.jav

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.adolfosalado.jav.databinding.ActivityAnimationBinding

class AnimationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimationBinding

    private val TIMEOUT_DURATION = 4000L // Duración del timeout en milisegundos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeAnimation()
        intentWithDelay()
    }

    private fun intentWithDelay() {
        val handler = Handler()
        handler.postDelayed({
            // Crear un Intent para iniciar la nueva Activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish() // Opcional: finalizar la Activity actual después de iniciar la nueva Activity
        }, TIMEOUT_DURATION)
    }

    private fun getGoToIntent(): String {
        return intent.getStringExtra("goTo")!!
    }

    private fun changeAnimation() {

        when (getGoToIntent()) {
            "salta" -> {
                binding.animationView.setAnimation(R.raw.salta)
                binding.animationView.playAnimation()
            }

            "sword" -> {
                binding.animationView.setAnimation(R.raw.sword)
                binding.tvCongratulation.text =
                    "¡Enhorabuena! Has conseguido la Espada Constante"
            }

            "shield" -> {
                binding.animationView.setAnimation(R.raw.sword)
                binding.tvCongratulation.text =
                    "¡Enhorabuena! Has conseguido el Escudo Variable"
            }

            "wand" -> {
                binding.animationView.setAnimation(R.raw.sword)
                binding.tvCongratulation.text =
                    "¡Enhorabuena! Has conseguido la Varita Condicional"
            }

            "arch" -> {
                binding.animationView.setAnimation(R.raw.sword)
                binding.tvCongratulation.text =
                    "¡Enhorabuena! Has conseguido el Arco Iterable"
            }

            else -> {
                TODO()
            }
        }
    }
}
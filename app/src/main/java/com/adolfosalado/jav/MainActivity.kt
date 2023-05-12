package com.adolfosalado.jav

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adolfosalado.jav.databinding.ActivityMainBinding
import com.adolfosalado.jav.utils.Preferences
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import kotlin.random.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val sentences: List<String> = listOf(
        "¡Me alegro de verte, Jav!",
        "¡Hola! Soy Titi",
        "¿Qué tal la aventura?",
        "¿Realizamos una lección?",
        "01000101 01110011 01110100 01100101 01100110 01111001",
        "¡Vamos a por Nully!",
        "¿Tomamos una siesta?",
        "¡BIP, BIP!"
    )

    private val sentencesAfterWin: List<String> = listOf(
        "¡Pudimos con Nully!",
        "Qué bonito es Reino Noria",
        "Por fin podemos descansar",
        "¡Volvamos a Reino Tiku!"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        getCharacterInfo()

    }

    private fun initViews() {
        binding.ivTiti.adjustViewBounds
        Glide.with(this)
            .load(R.drawable.computer)
            .into(binding.ivTiti)

        binding.ivTiti.adjustViewBounds
        Glide.with(this)
            .load(R.drawable.crown)
            .into(binding.ivCrown)

        showSentence()

        controllOfImageClickListener()

    }

    private fun controllOfImageClickListener() {
        val level = Preferences.recoverLevelOfUserInSharedPreferences(this)?.toInt()!!

        if (level == 3) {
            binding.ivSword.setImageResource(R.drawable.sword)
            binding.ivSword.setOnClickListener {
                TODO()
            }
        }

        if(level == 5) {
            binding.ivShield.setImageResource(R.drawable.shield)
            binding.ivShield.setOnClickListener {
                TODO()
            }
        }

        if (level == 7) {
            binding.ivWand.setImageResource(R.drawable.wand)
            binding.ivWand.setOnClickListener {
                TODO()
            }
        }

        if (level == 9) {
            binding.ivArch.setImageResource(R.drawable.arch)
            binding.ivArch.setOnClickListener {
                TODO()
            }
        }
    }

    private fun getCharacterInfo() {
        val level = Preferences.recoverLevelOfUserInSharedPreferences(this)?.toInt()!!

        binding.tvLevel.text = level.toString()
        binding.btnLessons.setOnClickListener {
            val intent = Intent(this@MainActivity, RecyclerViewLessonActivity::class.java)
            startActivity(intent)


            val imageView = LottieAnimationView(this)
            imageView.setAnimation(R.raw.celebration)
            imageView.playAnimation()
        }
    }

    private fun showSentence() {
        val level = Preferences.recoverLevelOfUserInSharedPreferences(this)?.toInt()!!

        if (level < 11) {
            val randomNumber = Random.nextInt(sentences.size)
            binding.tvSentence.text = sentences[randomNumber]

        } else {
            val randomNumber = Random.nextInt(sentencesAfterWin.size)
            binding.tvSentence.text = sentences[randomNumber]
        }

        binding.ivTiti.setOnClickListener {
            if (level < 11) {
                val randomNumber = Random.nextInt(sentences.size)
                binding.tvSentence.text = sentences[randomNumber]

            } else {
                val randomNumber = Random.nextInt(sentencesAfterWin.size)
                binding.tvSentence.text = sentences[randomNumber]
            }
        }
    }
}
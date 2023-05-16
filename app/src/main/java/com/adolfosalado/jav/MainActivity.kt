package com.adolfosalado.jav

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
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

        if (isInternetAvailable(this)) {
            initViews()

            getCharacterInfo()
        } else {
            val intent = Intent(this, ConnectionActivity::class.java)
            startActivity(intent)
        }

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

        if (level >= 4) {
            binding.ivSword.setImageResource(R.drawable.sword)
            binding.ivSword.setOnClickListener {
                val intent = Intent(this, ToolDetail::class.java)
                intent.putExtra("tool", "sword")
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }

        if(level >= 6) {
            binding.ivShield.setImageResource(R.drawable.shield)
            binding.ivShield.setOnClickListener {
                val intent = Intent(this, ToolDetail::class.java)
                intent.putExtra("tool", "shield")
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }

        if (level >= 8) {
            binding.ivWand.setImageResource(R.drawable.wand)
            binding.ivWand.setOnClickListener {
                val intent = Intent(this, ToolDetail::class.java)
                intent.putExtra("tool", "wand")
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }

        if (level > 9) {
            binding.ivArch.setImageResource(R.drawable.arch)
            binding.ivArch.setOnClickListener {
                val intent = Intent(this, ToolDetail::class.java)
                intent.putExtra("tool", "arch")
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }
    }

    private fun getCharacterInfo() {
        val level = Preferences.recoverLevelOfUserInSharedPreferences(this)?.toInt()!!

        binding.tvLevel.text = level.toString()

        if (level <= 10 ) {
            binding.tvLevel.text = level.toString()

        } else {
            binding.tvLevel.text = "NIVEL MÁXIMO"
        }

        binding.btnLessons.setOnClickListener {
            val intent = Intent(this@MainActivity, RecyclerViewLessonActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            
            val imageView = LottieAnimationView(this)
            imageView.setAnimation(R.raw.celebration)
            imageView.playAnimation()
        }
    }

    private fun showSentence() {
        val level = Preferences.recoverLevelOfUserInSharedPreferences(this)?.toInt()!!

        if (level <= 10) {
            val randomNumber = Random.nextInt(sentences.size)
            binding.tvSentence.text = sentences[randomNumber]

        } else if (level == 11){
            val randomNumber = Random.nextInt(sentencesAfterWin.size)
            binding.tvSentence.text = sentencesAfterWin[randomNumber]
        }

        binding.ivTiti.setOnClickListener {
            if (level <= 10) {
                val randomNumber = Random.nextInt(sentences.size)
                binding.tvSentence.text = sentences[randomNumber]

            } else if (level == 11) {
                val randomNumber = Random.nextInt(sentencesAfterWin.size)
                binding.tvSentence.text = sentencesAfterWin[randomNumber]
            }
        }
    }

    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    }
}
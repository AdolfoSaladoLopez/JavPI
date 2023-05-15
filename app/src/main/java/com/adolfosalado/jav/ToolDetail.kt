package com.adolfosalado.jav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.adolfosalado.jav.api.ApiService
import com.adolfosalado.jav.api.Retrofit
import com.adolfosalado.jav.databinding.ActivityToolDetailBinding
import com.adolfosalado.jav.models.Weapon
import kotlinx.coroutines.launch

class ToolDetail : AppCompatActivity() {
    private lateinit var binding: ActivityToolDetailBinding
    private var listOfWeapons: List<Weapon>? = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToolDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            setImageAndDescriptionOfImage()
        }

    }

    private fun getIntentId(): String? {
        return intent.getStringExtra("tool")
    }

    private suspend fun setImageAndDescriptionOfImage() {
        val apiService = Retrofit.getRetrofit().create(ApiService::class.java)


        listOfWeapons = apiService.getWeapons().body()

        val nameOfTool = getIntentId()

        when (nameOfTool) {
            "sword" -> {
                binding.animationView.setAnimation(R.raw.sword)
                binding.tvTool.text = listOfWeapons?.get(0)?.name?.trim()
                binding.tvToolDescription.text = listOfWeapons?.get(0)?.description?.trim()
                binding.tvDate.text =
                    "Conseguida en la lección ${listOfWeapons?.get(0)?.lessonId}"
            }

            "shield" -> {
                binding.animationView.setAnimation(R.raw.shield)
                binding.tvTool.text = listOfWeapons?.get(1)?.name?.trim()
                binding.tvToolDescription.text = listOfWeapons?.get(1)?.description?.trim()
                binding.tvDate.text =
                    "Conseguida en la lección ${listOfWeapons?.get(1)?.lessonId}"
            }

            "wand" -> {
                binding.animationView.setAnimation(R.raw.wand)
                binding.tvTool.text = listOfWeapons?.get(2)?.name?.trim()
                binding.tvToolDescription.text = listOfWeapons?.get(2)?.description?.trim()
                binding.tvDate.text =
                    "Conseguida en la lección ${listOfWeapons?.get(2)?.lessonId}"
            }

            "arch" -> {
                binding.animationView.setAnimation(R.raw.bow)
                binding.tvTool.text = listOfWeapons?.get(3)?.name?.trim()
                binding.tvToolDescription.text = listOfWeapons?.get(3)?.description?.trim()
                binding.tvDate.text =
                    "Conseguida en la lección ${listOfWeapons?.get(3)?.lessonId}"
            }
        }
    }
}
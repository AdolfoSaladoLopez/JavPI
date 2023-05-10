package com.adolfosalado.jav

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.adolfosalado.jav.api.ApiService
import com.adolfosalado.jav.api.Retrofit.Companion.getRetrofit
import com.adolfosalado.jav.databinding.ActivityMainBinding
import com.adolfosalado.jav.session.SessionData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getCharacter()
    }

    private fun getCharacter() {
        val apiService = getRetrofit().create(ApiService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val call = apiService.getDatos()
            val character = call.body()?.get(0)
            SessionData.levelUser = character?.level.toString()

            if (call.isSuccessful) {
                binding.tvLevel.text = character?.level
                binding.btnLessons.setOnClickListener {
                    val intent = Intent(this@MainActivity, RecyclerViewLessonActivity::class.java)
                    startActivity(intent)
                }
            } else {
                Log.i("Character", "fail")
            }
        }
    }
}
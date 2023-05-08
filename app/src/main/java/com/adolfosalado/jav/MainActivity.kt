package com.adolfosalado.jav

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adolfosalado.jav.api.ApiService
import com.adolfosalado.jav.databinding.ActivityMainBinding
import com.adolfosalado.jav.models.Character
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getLesson()


    }

    private fun getCharacter() {
        val BASE_URL = "https://adolfodev-jav.azurewebsites.net/"
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Si estás utilizando Gson para convertir las respuestas
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        CoroutineScope(Dispatchers.Main).launch {
            val call = apiService.getDatos()
            val character = call.body()

            if (call.isSuccessful) {
                character?.get(0)?.level?.let { Log.i("Character", it) }
            } else {
                Log.i("Character", "fail")

            }
        }
    }

    private fun getLesson() {
        val BASE_URL = "https://adolfodev-jav.azurewebsites.net/"
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Si estás utilizando Gson para convertir las respuestas
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        CoroutineScope(Dispatchers.Main).launch {
            val call = apiService.getLessons()
            val character = call.body()
            val completed = character?.get(0)?.completed
            val user = character?.get(0)?.userId

            if (call.isSuccessful) {
                Log.i("Character", "$user")
            } else {
                Log.i("Character", "fail")

            }
        }
    }
}
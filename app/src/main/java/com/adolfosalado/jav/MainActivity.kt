package com.adolfosalado.jav

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.adolfosalado.jav.api.ApiService
import com.adolfosalado.jav.databinding.ActivityMainBinding
import com.adolfosalado.jav.models.Lesson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var listLessons = getLessons()

        listLessons.forEach {
            Log.i("Character", it.name)
        }



    }

    private fun getCharacter() {
        val apiService = getRetrofit().create(ApiService::class.java)

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

    private fun getLessons(): MutableList<Lesson> {
        val apiService = getRetrofit().create(ApiService::class.java)

        val lessons: MutableList<Lesson> = mutableListOf()


        lifecycleScope.launch {
            val call = apiService.getLessons()
            lessons.addAll(call.body()!!)
            Log.i("Character", lessons[0].name)
        }


        return lessons
    }

    private fun getRetrofit(): Retrofit {
        val BASE_URL = "https://adolfodev-jav.azurewebsites.net/"
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Si estás utilizando Gson para convertir las respuestas
            .build()
        return retrofit
    }

    fun obtenerBytesDeImagenDesdeRecurso(context: Context, resourceId: Int): ByteArray {
        val resources: Resources = context.resources
        val bitmap: Bitmap = BitmapFactory.decodeResource(resources, resourceId)
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val bytes = stream.toByteArray()
        stream.close()
        return bytes
    }
}
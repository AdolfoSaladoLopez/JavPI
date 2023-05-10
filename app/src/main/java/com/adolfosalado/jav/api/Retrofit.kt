package com.adolfosalado.jav.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {
    companion object {
        private val BASE_URL = "https://adolfodev-jav.azurewebsites.net/"

        fun getRetrofit():Retrofit {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // Si estás utilizando Gson para convertir las respuestas
                .build()
            return retrofit
        }
    }
}
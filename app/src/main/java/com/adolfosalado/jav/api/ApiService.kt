package com.adolfosalado.jav.api

import com.adolfosalado.jav.models.Character
import com.adolfosalado.jav.models.Lesson
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("api/Character")
    suspend fun getDatos(): Response<List<Character>>

    @GET("api/Lesson")
    suspend fun getLessons(): Response<List<Lesson>>
}
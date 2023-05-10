package com.adolfosalado.jav.api

import com.adolfosalado.jav.models.Character
import com.adolfosalado.jav.models.Lesson
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/Character")
    suspend fun getDatos(): Response<List<Character>>

    @GET("api/Lesson")
    suspend fun getLessons(): Response<List<Lesson>>

    @GET("api/Lesson/{id}")
    suspend fun getLessonById(@Path("id") id: String): Response<Lesson>
}
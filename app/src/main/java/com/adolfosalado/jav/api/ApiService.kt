package com.adolfosalado.jav.api

import com.adolfosalado.jav.models.Answer
import com.adolfosalado.jav.models.Character
import com.adolfosalado.jav.models.Lesson
import com.adolfosalado.jav.models.Question
import com.adolfosalado.jav.models.Weapon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/Character")
    suspend fun getDatos(): Response<List<Character>>

    @GET("api/Lesson")
    suspend fun getLessons(): Response<List<Lesson>>

    @GET("api/Question")
    suspend fun getQuestions(): Response<List<Question>>

    @GET("api/Answer")
    suspend fun getAnswers(): Response<List<Answer>>

    @GET("api/Weapon")
    suspend fun getWeapons(): Response<List<Weapon>>

    @GET("api/Lesson/{id}")
    suspend fun getLessonById(@Path("id") id: String): Response<Lesson>
}
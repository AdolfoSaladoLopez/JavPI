package com.adolfosalado.jav.models

import com.google.gson.annotations.SerializedName

data class Question(
    var id: String,
    var name: String,
    @SerializedName("question1") var question: String,
    var lessonId: String,
    var image: Int,
    var level: Int,
    var respuestas: MutableList<Answer>
)
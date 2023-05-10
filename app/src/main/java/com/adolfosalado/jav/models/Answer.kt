package com.adolfosalado.jav.models

import com.google.gson.annotations.SerializedName

data class Answer(
    var id: String,
    @SerializedName("answer1") var answer: String,
    var correct: Boolean,
    var questionId: String,
    var image: Int
)

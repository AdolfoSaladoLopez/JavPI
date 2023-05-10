package com.adolfosalado.jav.models

data class QuestionAnswers(
    var question: Question,
    var answers: MutableList<Answer>?
)

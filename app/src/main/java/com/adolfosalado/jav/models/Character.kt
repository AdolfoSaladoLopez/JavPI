package com.adolfosalado.jav.models

data class Character(
    var id: String,
    var level: String,
    var lessons: List<Lesson>,
    var weapons: List<String>
)
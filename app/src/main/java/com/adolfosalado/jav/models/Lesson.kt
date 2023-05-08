package com.adolfosalado.jav.models

import android.graphics.Bitmap

data class Lesson(
    var id: String,
    var name: String,
    var firstDescription: String,
    var firstImage: Bitmap,
    var secondDescription: String,
    var secondImage: Bitmap,
    var thirdDescription: String,
    var userId: String,
    val completed: Boolean,
    var question: List<String>,
    var user: Character,
    var weapons: List<String>


)

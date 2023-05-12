package com.adolfosalado.jav.adapter

import android.content.Context
import android.graphics.Color
import android.opengl.Visibility
import android.os.Vibrator
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.adolfosalado.jav.R
import com.adolfosalado.jav.databinding.ItemLessonsBinding
import com.adolfosalado.jav.models.Lesson
import com.adolfosalado.jav.utils.Preferences

class LessonViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemLessonsBinding.bind(view)

    fun render(lesson: Lesson, onClicklistener: (Lesson) -> Unit) {
        //binding.ivLesson = lesson.firstImage

        if (lesson.id.toInt() > Preferences.recoverLevelOfUserInSharedPreferences(itemView.context)
                ?.toInt()!!
        ) {
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "No tienes el nivel", Toast.LENGTH_SHORT).show()
            }
        } else {
            itemView.setOnClickListener { onClicklistener(lesson) }
        }

        binding.tvName.text = lesson.name
        binding.tvSubtitle.text = lesson.name

    }
}
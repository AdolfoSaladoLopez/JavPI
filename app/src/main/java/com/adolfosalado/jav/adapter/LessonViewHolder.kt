package com.adolfosalado.jav.adapter

import android.annotation.SuppressLint
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.adolfosalado.jav.R
import com.adolfosalado.jav.databinding.ItemLessonsBinding
import com.adolfosalado.jav.models.Lesson
import com.adolfosalado.jav.utils.Preferences

class LessonViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemLessonsBinding.bind(view)

    @SuppressLint("SetTextI18n")
    fun render(lesson: Lesson, onClicklistener: (Lesson) -> Unit) {
        setImage(lesson.id)

        if (lesson.id.toInt() > Preferences.recoverLevelOfUserInSharedPreferences(itemView.context)
                ?.toInt()!!
        ) {
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "No tienes el nivel", Toast.LENGTH_SHORT).show()
            }
        } else {
            itemView.setOnClickListener { onClicklistener(lesson) }
        }

        binding.tvName.text = "LECCIÓN ${lesson.id}"
        setSubtitle(lesson.id)

    }

    private fun setImage(lessonId: String) {
        when (lessonId) {
            "1" -> {
                binding.ivLesson.setImageResource(R.drawable.jav_person)
            }

            "2" -> {
                binding.ivLesson.setImageResource(R.drawable.koco)
            }

            "3" -> {
                binding.ivLesson.setImageResource(R.drawable.bosque)
            }

            "4" -> {
                binding.ivLesson.setImageResource(R.drawable.colorful)
            }

            "5" -> {
                binding.ivLesson.setImageResource(R.drawable.tower)
            }

            "6" -> {
                binding.ivLesson.setImageResource(R.drawable.maze)
            }

            "7" -> {
                binding.ivLesson.setImageResource(R.drawable.sleep)
            }

            "8" -> {
                binding.ivLesson.setImageResource(R.drawable.noria)
            }

            "9" -> {
                binding.ivLesson.setImageResource(R.drawable.tiovivo)
            }

            "10" -> {
                binding.ivLesson.setImageResource(R.drawable.castle)
            }
        }
    }

    private fun setSubtitle(lessonId: String) {
        when (lessonId) {
            "1" -> {
                binding.tvSubtitle.text = "La elegida"
            }

            "2" -> {
                binding.tvSubtitle.text = "Viaje a Reino Koco"
            }

            "3" -> {
                binding.tvSubtitle.text = "Conociendo al Rey Constante"
            }

            "4" -> {
                binding.tvSubtitle.text = "Viaje a Reino Violeta"
            }

            "5" -> {
                binding.tvSubtitle.text = "Subir a la torre"
            }

            "6" -> {
                binding.tvSubtitle.text = "La senda del laberinto"
            }

            "7" -> {
                binding.tvSubtitle.text = "Tomando decisiones"
            }

            "8" -> {
                binding.tvSubtitle.text = "Bienvenida a Reino Noria"
            }

            "9" -> {
                binding.tvSubtitle.text = "Ayudando a la Reina Giro"
            }

            "10" -> {
                binding.tvSubtitle.text = "Enfrentarse a Nully"
            }
        }
    }

}
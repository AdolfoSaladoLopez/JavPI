package com.adolfosalado.jav.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.adolfosalado.jav.databinding.ItemLessonsBinding
import com.adolfosalado.jav.models.Lesson

class LessonViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemLessonsBinding.bind(view)


    fun render(lesson: Lesson, onClicklistener: (Lesson) -> Unit) {
        //binding.ivLesson = lesson.firstImage
        binding.tvName.text = lesson.name
        binding.tvSubtitle.text = lesson.name

        itemView.setOnClickListener { onClicklistener(lesson) }
    }
}
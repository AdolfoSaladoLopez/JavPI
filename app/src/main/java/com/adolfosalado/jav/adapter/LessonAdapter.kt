package com.adolfosalado.jav.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adolfosalado.jav.R
import com.adolfosalado.jav.models.Lesson

class LessonAdapter(
    private var lessonsList: List<Lesson>,
    private val onClickListener: (Lesson) -> Unit
) :
    RecyclerView.Adapter<LessonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_lessons, parent, false)
        return LessonViewHolder(view)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val item = lessonsList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = lessonsList.size
}
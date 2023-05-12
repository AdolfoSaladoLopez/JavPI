package com.adolfosalado.jav.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adolfosalado.jav.R
import com.adolfosalado.jav.models.Lesson
import com.adolfosalado.jav.utils.Preferences

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

        if (item.id.toInt() > Preferences.recoverLevelOfUserInSharedPreferences(holder.itemView.context)
                ?.toInt()!!
        ) {
            holder.binding.grayOverlay.visibility = View.VISIBLE
        } else {
            holder.binding.grayOverlay.visibility = View.GONE

        }

    }

    override fun getItemCount(): Int = lessonsList.size
}
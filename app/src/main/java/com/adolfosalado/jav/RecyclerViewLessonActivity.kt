package com.adolfosalado.jav

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.adolfosalado.jav.adapter.LessonAdapter
import com.adolfosalado.jav.api.ApiService
import com.adolfosalado.jav.api.Retrofit.Companion.getRetrofit
import com.adolfosalado.jav.databinding.ActivityRecyclerViewLessonBinding
import com.adolfosalado.jav.models.Lesson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecyclerViewLessonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewLessonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewLessonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvLesson.layoutManager = LinearLayoutManager(this)

        val apiService = getRetrofit().create(ApiService::class.java)

        CoroutineScope(Dispatchers.Main).launch {
            val call = apiService.getLessons()

            if (call.isSuccessful) {
                binding.rvLesson.adapter = LessonAdapter(call.body()!!) {
                    onItemSelected(it)
                }
            }
        }
    }

    private fun onItemSelected(lesson: Lesson) {
        Toast.makeText(this, lesson.name, Toast.LENGTH_LONG).show()
    }
}
package com.adolfosalado.jav

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
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

        lifecycleScope.launch {
            val call = apiService.getLessons()

            if (call.isSuccessful) {
                binding.rvLesson.adapter = LessonAdapter(call.body()!!) {
                    onItemSelected(it)
                }
            } else {
                Toast.makeText(
                    this@RecyclerViewLessonActivity,
                    "No es posible cargar datos",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun onItemSelected(lesson: Lesson) {
        val intent = Intent(this, LessonActivity::class.java)
        intent.putExtra("id", lesson.id)
        startActivity(intent)
    }
}
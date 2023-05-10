package com.adolfosalado.jav

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adolfosalado.jav.adapter.LessonAdapter
import com.adolfosalado.jav.api.ApiService
import com.adolfosalado.jav.api.Retrofit
import com.adolfosalado.jav.databinding.ActivityLessonBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LessonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLessonBinding
    private var lessonId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lessonId = getLessonId()
        showLesson(lessonId)


    }

    private fun getLessonId(): String? {
        return intent.getStringExtra("id")
    }

    private fun showLesson(lessonId: String?) {
        val apiService = Retrofit.getRetrofit().create(ApiService::class.java)

        CoroutineScope(Dispatchers.Main).launch {
            val call = lessonId?.let { apiService.getLessonById(it) }

            if (call != null && call.isSuccessful) {
                val lesson = call.body()

                binding.tvTitleLesson.text = lesson?.name
                binding.tvFirstDescription.text = lesson?.firstDescription
                binding.ivFirstImage.setImageResource(R.drawable.jav)
                binding.tvSecondDescription.text = lesson?.secondDescription
                binding.ivSecondImage.setImageResource(R.drawable.jav)
                binding.tvThirdDescription.text = lesson?.thirdDescription

                binding.btnGoToExercise.setOnClickListener {
                    val intent = Intent(it.context, ExerciseActivity::class.java)
                    intent.putExtra("id", lessonId)
                    startActivity(intent)
                }
            }
        }
    }
}
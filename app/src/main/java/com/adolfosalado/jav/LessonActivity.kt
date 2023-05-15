package com.adolfosalado.jav

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
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

        lifecycleScope.launch {
            val call = lessonId?.let { apiService.getLessonById(it) }

            if (call!!.isSuccessful) {
                val lesson = call.body()

                binding.tvTitleLesson.text = lesson?.name?.trim()
                binding.tvFirstDescription.text = lesson?.firstDescription?.trim()
                addImageLesson()
                binding.tvSecondDescription.text = lesson?.secondDescription?.trim()
                binding.tvThirdDescription.text = lesson?.thirdDescription?.trim()

                binding.btnGoToExercise.setOnClickListener {
                    val intent = Intent(it.context, ExerciseActivity::class.java)
                    intent.putExtra("id", lessonId)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

                }
            }
        }
    }

    private fun addImageLesson() {
        when (lessonId) {
            "1" -> {
                binding.ivFirstImage.setImageResource(R.drawable.jav_person)
            }
            "2" -> {
                binding.ivFirstImage.setImageResource(R.drawable.koco)
            }
            "3" -> {
                binding.ivFirstImage.setImageResource(R.drawable.bosque)
            }
            "4" -> {
                binding.ivFirstImage.setImageResource(R.drawable.colorful)
            }
            "5" -> {
                binding.ivFirstImage.setImageResource(R.drawable.tower)
            }
            "6" -> {
                binding.ivFirstImage.setImageResource(R.drawable.maze)
            }
            "7" -> {
                binding.ivFirstImage.setImageResource(R.drawable.sleep)
            }
            "8" -> {
                binding.ivFirstImage.setImageResource(R.drawable.noria)
            }
            "9" -> {
                binding.ivFirstImage.setImageResource(R.drawable.tiovivo)
            }
            "10" -> {
                binding.ivFirstImage.setImageResource(R.drawable.castle)
            }
        }
    }
}
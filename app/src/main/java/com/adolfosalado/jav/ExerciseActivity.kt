package com.adolfosalado.jav

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.adolfosalado.jav.api.ApiService
import com.adolfosalado.jav.api.Retrofit
import com.adolfosalado.jav.databinding.ActivityExerciseBinding
import com.adolfosalado.jav.models.Answer
import com.adolfosalado.jav.models.Question
import com.adolfosalado.jav.models.QuestionAnswers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseBinding
    private var indexQuestion = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lessonId = getLessonId()
        getQuestions(lessonId)
    }

    private fun getLessonId(): String? {
        return intent.getStringExtra("id")
    }

    private fun getQuestions(lessonId: String?) {
        val apiService = Retrofit.getRetrofit().create(ApiService::class.java)

        CoroutineScope(Dispatchers.Main).launch {
            val callQuestion = apiService.getQuestions()
            val callAnswer = apiService.getAnswers()

            if (callQuestion.isSuccessful && callAnswer.isSuccessful) {
                val questionsOfTheLesson: MutableList<Question> = mutableListOf()
                val listOfQuestionsWithAnswers: MutableList<QuestionAnswers> = mutableListOf()

                lateinit var currentQuestion: Question
                lateinit var currentQuestionAnswers: QuestionAnswers

                val questions = callQuestion.body()
                val answers = callAnswer.body()

                questions?.forEach { question ->
                    if (question.lessonId == lessonId) {
                        questionsOfTheLesson.add(question)
                    }
                }

                for (question in questionsOfTheLesson) {
                    val answersOfTheCurrentQuestion: MutableList<Answer> = mutableListOf()
                    currentQuestion = question

                    if (answers != null) {
                        for (answer in answers) {
                            if (question.id == answer.questionId) {
                                answersOfTheCurrentQuestion.add(answer)
                            }
                        }
                    }

                    currentQuestionAnswers =
                        QuestionAnswers(currentQuestion, answersOfTheCurrentQuestion)

                    listOfQuestionsWithAnswers.add(currentQuestionAnswers)
                }

                showQuestion(listOfQuestionsWithAnswers)
            }
        }
    }

    private fun showQuestion(
        listOfQuestionsWithAnswers: List<QuestionAnswers>
    ) {

        val actualQuestion = listOfQuestionsWithAnswers[indexQuestion]
        val currentAnswers = actualQuestion.answers
        val answerCorrect = currentAnswers?.find { it.correct }

        binding.ivQuestion.setImageResource(R.drawable.jav)
        binding.tvTitleOfQuestion.text = actualQuestion.question.name.trim()
        binding.tvTextOfQuestion.text = actualQuestion.question.question.trim()
        binding.rb1.text = currentAnswers?.get(0)?.answer?.trim()
        binding.rb2.text = currentAnswers?.get(1)?.answer?.trim()
        binding.rb3.text = currentAnswers?.get(2)?.answer?.trim()
        binding.rb4.text = currentAnswers?.get(3)?.answer?.trim()

        binding.btnAnswer.setOnClickListener {
            val idRadioButton = binding.rgAnswer.checkedRadioButtonId
            val radioButtonSelected = findViewById<RadioButton>(idRadioButton)

            if (radioButtonSelected.text.trim() == answerCorrect?.answer?.trim()) {
                Toast.makeText(it.context, "Has acertado", Toast.LENGTH_LONG).show()
                indexQuestion++

                if (indexQuestion < listOfQuestionsWithAnswers.size) {
                    showQuestion(listOfQuestionsWithAnswers)
                } else {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("¡Enhorabuena!")
                    builder.setMessage("Has acertado todas.")

                    builder.setPositiveButton("OK") { dialog, _ ->
                        val intent = Intent(this, RecyclerViewLessonActivity::class.java)
                        startActivity(intent)
                        dialog.dismiss()

                    }
                    val alertDialog = builder.create()
                    alertDialog.show()
                }
            } else {
                Toast.makeText(it.context, "No has acertado", Toast.LENGTH_LONG).show()
            }
        }
    }
}
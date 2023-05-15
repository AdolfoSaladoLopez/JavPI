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
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class ExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseBinding
    private var indexQuestion = 0
    private var lessonId: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lessonId = getLessonId()?.toInt()

        if (lessonId == 10) {
            getQuestionsOfLessonTen()
        } else {
            getQuestions(lessonId?.toString())

        }
    }

    private fun getLessonId(): String? {
        return intent.getStringExtra("id")
    }

    private fun getQuestionsOfLessonTen() {
        val apiService = Retrofit.getRetrofit().create(ApiService::class.java)
        val listOfQuestionsWithAnswers: MutableList<QuestionAnswers> = mutableListOf()

        CoroutineScope(Dispatchers.Main).launch {
            val callQuestion = apiService.getQuestions()
            val callAnswer = apiService.getAnswers()

            if (callQuestion.isSuccessful && callAnswer.isSuccessful) {
                val allQuestions: List<Question>? = callQuestion.body()
                val allAnswers: List<Answer>? = callAnswer.body()
                lateinit var currentQuestion: Question
                lateinit var currentQuestionAnswers: QuestionAnswers

                allQuestions?.forEach { question ->
                    val answersOfTheCurrentQuestion: MutableList<Answer> = mutableListOf()
                    currentQuestion = question

                    allAnswers?.forEach { answer ->
                        if (question.id == answer.questionId) {
                            answersOfTheCurrentQuestion.add(answer)
                        }
                    }

                    currentQuestionAnswers =
                        QuestionAnswers(currentQuestion, answersOfTheCurrentQuestion)

                    listOfQuestionsWithAnswers.add(currentQuestionAnswers)
                }

                val tenQuestionList: MutableList<QuestionAnswers> = mutableListOf()

                for (i in 0 until 10) {
                    val randomNumber = Random.nextInt(listOfQuestionsWithAnswers.size)

                    if (!tenQuestionList.contains(listOfQuestionsWithAnswers[randomNumber])) {
                        tenQuestionList.add(listOfQuestionsWithAnswers[randomNumber])
                    }
                }

                showQuestion(tenQuestionList)
            }

        }
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

        Glide.with(this)
            .load(R.drawable.eyes)
            .into(binding.ivQuestion)
        binding.tvTitleOfQuestion.text = "Pregunta ${(indexQuestion + 1)}"
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
                    binding.rgAnswer.check(R.id.rb1)
                    showQuestion(listOfQuestionsWithAnswers)
                } else {

                    val preferences = this.getSharedPreferences(
                        "sharedPreferences",
                        MODE_PRIVATE
                    )

                    val editor = preferences.edit()
                    val lastLevel = preferences.getString("level", "")
                    var goTo = "salta"

                    if (lastLevel?.toInt()!! == lessonId) {
                        editor.putString("level", (lastLevel.toInt().plus(1)).toString())
                        editor.apply()

                        if (lessonId == 3 || lessonId == 5 || lessonId == 7 || lessonId == 9 || lessonId == 10) {
                            goTo = when (lessonId) {
                                3 -> {
                                    "sword"
                                }

                                5 -> {
                                    "shield"
                                }

                                7 -> {
                                    "wand"
                                }

                                9 -> {
                                    "arch"
                                }

                                10 -> {
                                    "win"
                                }

                                else -> {
                                    "salta"
                                }
                            }
                        }
                    }

                    if (lessonId == 10) {
                        goTo = "win"
                    }

                    val intent = Intent(this, AnimationActivity::class.java)
                    intent.putExtra("goTo", goTo)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
            } else {
                val builder = MaterialAlertDialogBuilder(this, R.style.AlertDialogStyle)
                builder.setTitle("Error")
                builder.setMessage("¡Esa no es la respuesta! Vuelve a intentarlo.")
                builder.setPositiveButton("Aceptar") { dialog, which ->
                    dialog.dismiss()
                }
                builder.show()
            }
        }
    }

}
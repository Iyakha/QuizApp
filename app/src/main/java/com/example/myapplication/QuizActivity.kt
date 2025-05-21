package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizActivity : AppCompatActivity() {
    private val questions = arrayOf(
        "A South African scientist once crossbred a human and a chimpanzee.",
        "South Africa once had an official 'Minister of Witchcraft' in government.",
        "The world's largest diamond was found in South Africa.",
        "Cyril Ramaphosa was the first Black president of South Africa.",
        "The San people were the earliest known inhabitants of South Africa"
    )

    private val answers = booleanArrayOf(false, true, true, false, true)

    private var currentQuestionIndex = 0
    private var score = 0
    private var answerSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)

        val totalQuestions = intent.getIntExtra("Score", 0)
        val score = intent.getIntExtra("Total Questions", 5)
        val feedbackText = findViewById<TextView>(R.id.feedbackText)
        val questionText = findViewById<TextView>(R.id.questionText)
        val trueButton = findViewById<Button>(R.id.trueButton)
        val falseButton = findViewById<Button>(R.id.falseButton)
        val nextButton = findViewById<Button>(R.id.nextButton)


        updateQuestion()

        trueButton.setOnClickListener {
            if (!answerSelected){
                checkAnswer(true)
                answerSelected = true
            }
        }

        falseButton.setOnClickListener {
            if (!answerSelected){
                checkAnswer(false)
                answerSelected = true
            }
        }

        nextButton.setOnClickListener {
            if (answerSelected){
                currentQuestionIndex++
                if(currentQuestionIndex < questions.size){
                    updateQuestion()
                    answerSelected = false
                    feedbackText.visibility = TextView.INVISIBLE
                } else{
                    //Quiz finished, go to score screen
                    val intent = Intent(this, ReviewActivity::class.java)
                    intent.putExtra("SCORE", score)
                    intent.putExtra("TOTAL_QUESTIONS", totalQuestions)
                    startActivity(intent)
                    finish()
                }

            } else {
                Toast.makeText(this, "Please select an answer first", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateQuestion(){
        val questionText = findViewById<TextView>(R.id.questionText)

        questionText.text = questions[currentQuestionIndex]
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val feedbackText = findViewById<TextView>(R.id.feedbackText)

        if(userAnswer == answers[currentQuestionIndex]){
            score++
            feedbackText.text = "Correct!"
            feedbackText.setTextColor(getColor(android.R.color.holo_green_light))
        } else {
            feedbackText.text = "Incorrect!"
            feedbackText.setTextColor(getColor(android.R.color.holo_red_light))
        }
        feedbackText.visibility = TextView.VISIBLE
    }

}

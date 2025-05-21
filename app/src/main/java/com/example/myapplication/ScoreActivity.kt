package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)


        val score = intent.getIntExtra("Score", 0)
        val totalQuestions = intent.getIntExtra("totalQuestions", 1)

        val scoreText = findViewById<TextView>(R.id.scoreText)
        val feedbackText = findViewById<TextView>(R.id.feedbackText)
        val reviewButton = findViewById<Button>(R.id.reviewButton)
        val exitButton = findViewById<Button>(R.id.exitButton)

        scoreText.text = "Your score: $score/$totalQuestions"

        val feedback = when{
            score == totalQuestions -> "Perfect! You know your history!"
            score >= totalQuestions * 0.7 -> "Great Job!"
            score >= totalQuestions * 0.5 -> "Good Effort!"
            else -> "Keep practicing!"
        }
        feedbackText.text= feedback

        reviewButton.setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java)
            intent.putExtra("score", score)
            intent.putExtra("totalQuestions", totalQuestions)
            startActivity(intent)
        }
        exitButton.setOnClickListener {
            finishAffinity() //Clear all activities and exit the app
        }

    }
}
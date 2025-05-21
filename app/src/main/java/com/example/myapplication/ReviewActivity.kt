package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review)


        val score = intent.getIntExtra("score", 0)
        val totalQuestions = intent.getIntExtra("totalQuestions", 5)

        val reviewText =findViewById<TextView>(R.id.reviewText)

        val feedback = when {
            score == totalQuestions -> "Perfect! Well done!"
            score >= totalQuestions * 0.7 -> "Great job!"
            score >= totalQuestions * 0.5 -> "Nice effort!"
            else -> "Keep practicing!"
        }
        reviewText.text = """
            You scored $score out of $totalQuestions.
                
            Feedback: $feedback
            
            Review your answers here..
            """.trimIndent()



    }
}

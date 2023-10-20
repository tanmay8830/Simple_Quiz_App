package com.example.simple_quiz_app

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.simple_quiz_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val questions = arrayOf("What is the built- in database in android studio?" ,
        "What is the full from of APK in Android Devlopment?",
        "In which year, first android was released by the google")

    private val  options =  arrayOf(arrayOf("MySQL" , "SQLite" ,"Firebase"),
        arrayOf("Application programming interface" ,  "Android Programming interface" , "Android Package Information"),
        arrayOf("2010" , "2006" , "2008"))

    private val correctAnswer = arrayOf(1, 0 , 2)

    private var currentQuestionsIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestion()

        binding.optionButton1.setOnClickListener {
            checkAnswers(0)
        }

        binding.optionButton2.setOnClickListener {
            checkAnswers(1)
        }

        binding.optionButton3.setOnClickListener {
            checkAnswers(2)
        }

        binding.restartButton.setOnClickListener {
            restartQuiz()
        }

    }

    private fun correctButtonColors(buttonIndex : Int){
        when(buttonIndex){
            0 ->binding.optionButton1.setBackgroundColor(Color.GREEN)
            1 ->binding.optionButton2.setBackgroundColor(Color.GREEN)
            2 ->binding.optionButton3.setBackgroundColor(Color.GREEN)
        }
    }

    private fun wrongButton(buttonIndex: Int){
        when(buttonIndex){
            0 ->binding.optionButton1.setBackgroundColor(Color.RED)
            1 ->binding.optionButton2.setBackgroundColor(Color.RED)
            2 ->binding.optionButton3.setBackgroundColor(Color.RED)
        }

    }

    private fun resetButtoncolors(){
        binding.optionButton1.setBackgroundColor(Color.rgb(50,59,96))
        binding.optionButton2.setBackgroundColor(Color.rgb(50,59,96))
        binding.optionButton3.setBackgroundColor(Color.rgb(50,59,96))
    }

    private fun ShowResult(){
         Toast.makeText(this,"your score: $score out of ${questions.size}", Toast.LENGTH_LONG).show()
        binding.restartButton.isEnabled = true
    }
    private fun displayQuestion(){
        binding.questionText.text = questions[currentQuestionsIndex]
        binding.optionButton1.text = options[currentQuestionsIndex][0]
        binding.optionButton2.text = options[currentQuestionsIndex][1]
        binding.optionButton3.text = options[currentQuestionsIndex][2]
        resetButtoncolors()
    }

    private fun checkAnswers(selectedAswerIndex:Int){
        val CorrectAnswerIndex = correctAnswer[currentQuestionsIndex]

        if (selectedAswerIndex == CorrectAnswerIndex){
            score++
            correctButtonColors(selectedAswerIndex)
        }else{
            wrongButton(selectedAswerIndex)
            correctButtonColors(CorrectAnswerIndex)
        }
        if (currentQuestionsIndex < questions.size - 1){
            currentQuestionsIndex++
            binding.questionText.postDelayed({displayQuestion()}, 1000)
        }else{
            ShowResult()
        }
    }
    private fun restartQuiz(){
        currentQuestionsIndex = 0
        score = 0
        displayQuestion()
        binding.restartButton.isEnabled = false
    }
}
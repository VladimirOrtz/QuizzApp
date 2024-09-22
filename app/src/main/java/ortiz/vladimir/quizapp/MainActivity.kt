package ortiz.vladimir.quizapp

import android.content.ContentValues.TAG
import  android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ortiz.vladimir.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val quizzViewModel:QuizzViewModel by viewModels()//

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()//aparta espacio en pantalla

        binding = ActivityMainBinding.inflate(layoutInflater)//inflate convierte xml a clases u obgetos
        setContentView(binding.root)//rutea para que podamos usar los recursos

        binding.trueButton.setOnClickListener {view: View ->
            checkAnswer(true)
        }

        binding.falseButton.setOnClickListener {
            checkAnswer(false)
        }

        binding.nextButton.setOnClickListener {
            quizzViewModel.moveToNext()
            updateQuestion()
        }
        binding.prevButton.setOnClickListener {
            quizzViewModel.moveToPrev()
            updateQuestion()
        }

        updateQuestion()
        Log.d(TAG,"Pasé por el mpetodo onCreate")
        Log.d(TAG,"Tengo un  QuizViewModel:$quizzViewModel")
    }

    private fun updateQuestion(){
        val questionTextResId = quizzViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer = quizzViewModel.currentQuestionAnswer

        val messageResId = if(userAnswer == correctAnswer){
            R.string.correct_toast
        }else{
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId,Toast.LENGTH_LONG).show()
    }
}





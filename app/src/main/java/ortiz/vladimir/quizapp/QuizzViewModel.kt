package ortiz.vladimir.quizapp

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG="QuizzViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"//lavve para buscar

class QuizzViewModel(private val savedStateHandel:SavedStateHandle):ViewModel(){

    private val questionBank = listOf(
        Question(R.string.question_luna,false),
        Question(R.string.question_africa,false),
        Question(R.string.question_fisico,true),
        Question(R.string.pregunta_cambio_carrera, false)
    )

    private var currentIndex:Int
        get() = savedStateHandel.get(CURRENT_INDEX_KEY)?:0
        set(value) =savedStateHandel.set(CURRENT_INDEX_KEY,value)

    val currentQuestionAnswer:Boolean
        get() = questionBank[currentIndex].answer
    val currentQuestionText:Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext(){
        currentIndex = (currentIndex+1)%questionBank.size
    }

    fun moveToPrev(){
        currentIndex = if(currentIndex==0){
            questionBank.size-1
        }else
            (currentIndex-1)% questionBank.size
    }
}
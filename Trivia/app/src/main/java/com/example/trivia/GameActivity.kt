package com.example.trivia

import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler
import android.os.Looper

class GameActivity : AppCompatActivity() {

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null

    /*
    private var btnDating = findViewById<Button>(R.id.btn_dating)
    private var btnRelated = findViewById<Button>(R.id.btn_related)
    private var btnBoth = findViewById<Button>(R.id.btn_both)
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        mQuestionsList = Constants.getQuestions()

        setQuestion()

        val btnDating = findViewById<Button>(R.id.btn_dating)
        val btnRelated = findViewById<Button>(R.id.btn_related)
        val btnBoth = findViewById<Button>(R.id.btn_both)

        // buttons behaviour
        btnDating.setOnClickListener{
            mCurrentPosition++
            when {
                mCurrentPosition <= mQuestionsList!!.size -> {
                    setQuestion()
                } else ->
                {
                    // go to finish screen
                }
            }
        }

        btnRelated.setOnClickListener{

            val question = mQuestionsList?.get(mCurrentPosition - 1)

            if(question!!.answer == "Related")
                btnRelated.setBackgroundColor(Color.parseColor("#00FF00"))
            else{
                btnRelated.setBackgroundColor(Color.parseColor("#FF0000"))
                if(question.answer == "Both")
                    btnBoth.setBackgroundColor(Color.parseColor("#00FF00"))
                else
                    btnDating.setBackgroundColor(Color.parseColor("#00FF00"))
            }

            //

            Handler(Looper.getMainLooper()).postDelayed({
                // Your Code
                mCurrentPosition++
                when {
                    mCurrentPosition <= mQuestionsList!!.size -> {

                        setQuestion()

                        btnRelated.setBackgroundColor(Color.parseColor("#0000FF"))
                        btnDating.setBackgroundColor(Color.parseColor("#0000FF"))
                        btnBoth.setBackgroundColor(Color.parseColor("#0000FF"))
                    } else ->
                {
                    // go to finish screen
                }
                }
            }, 500)


        }

        btnBoth.setOnClickListener{
            mCurrentPosition++
            when {
                mCurrentPosition <= mQuestionsList!!.size -> {
                    setQuestion()
                } else ->
            {
                // go to finish screen
            }
            }
        }
        
    }

    private fun setQuestion() {

        //resetButtonsColors()

        val question = mQuestionsList!![mCurrentPosition-1]

        val text = findViewById<TextView>(R.id.tv_question)
        val image1 = findViewById<ImageView>(R.id.iv_image1)
        val image2 = findViewById<ImageView>(R.id.iv_image2)

        text.text = question.question
        image1.setImageResource(question.firstImage)
        image2.setImageResource(question.secondImage)
    }

    /*
    private fun resetButtonsColors(){
        btnRelated.setBackgroundColor(Color.parseColor("#FF3700B3"))
        btnBoth.setBackgroundColor(Color.parseColor("#FF3700B3"))
        btnDating.setBackgroundColor(Color.parseColor("#FF3700B3"))
    }*/
}
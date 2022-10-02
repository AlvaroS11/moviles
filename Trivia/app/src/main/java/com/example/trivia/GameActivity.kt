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

    public var correct = 0

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
            handleRespone(btnRelated, btnBoth, btnDating,0)
        }

        btnRelated.setOnClickListener{
            handleRespone(btnRelated, btnBoth, btnDating,1)
        }

        btnBoth.setOnClickListener{
            handleRespone(btnRelated, btnBoth, btnDating,2)
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

    private fun handleRespone(btnRelated: Button, btnBoth: Button, btnDating: Button, whoPressed: Int){
        val counter = findViewById<TextView>(R.id.Counter)
        val question = mQuestionsList?.get(mCurrentPosition - 1)

        if(question!!.answer == "Dating") {
            btnDating.setBackgroundColor(Color.parseColor("#00FF00"))//verde
            if (whoPressed==1)
                btnRelated.setBackgroundColor(Color.parseColor("#FF0000"))
            else if (whoPressed==2)
                btnBoth.setBackgroundColor(Color.parseColor("#FF0000"))
            else {
                correct++
            }
        }
        else if(question!!.answer == "Related") {
            btnRelated.setBackgroundColor(Color.parseColor("#00FF00"))//green
            if (whoPressed==0)
                btnDating.setBackgroundColor(Color.parseColor("#FF0000"))
            else if(whoPressed==2)
                btnBoth.setBackgroundColor(Color.parseColor("#FF0000"))
            else
                correct++
        }
        else if(question!!.answer == "Both"){
            btnBoth.setBackgroundColor(Color.parseColor("#00FF00"))//Green
            if(whoPressed==0)
                btnDating.setBackgroundColor(Color.parseColor("#FF0000"))
            else if (whoPressed==1)
                btnRelated.setBackgroundColor(Color.parseColor("#FF0000"))
            else
                correct++
        }
        counter.text = correct.toString()
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
                println("NO QUEDAN PREGUNTAS")
                // go to finish screen
            }
            }
        }, 500)


    }

    /*
    private fun resetButtonsColors(){
        btnRelated.setBackgroundColor(Color.parseColor("#FF3700B3"))
        btnBoth.setBackgroundColor(Color.parseColor("#FF3700B3"))
        btnDating.setBackgroundColor(Color.parseColor("#FF3700B3"))
    }*/
}
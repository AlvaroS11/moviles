package com.example.datingorrelated

object Constants {
    fun getQuestions(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()


        val q1 = Question(1, "Zeus/Hera", R.drawable.zeus, R.drawable.hera, "Both")
        val q2 = Question(1, "Zeus/Metis", R.drawable.zeus, R.drawable.metis, "Dating")
        val q3 = Question(1, "Zeus/Hestia", R.drawable.zeus, R.drawable.hestia, "Related")

        questionsList.add(q1)
        questionsList.add(q2)
        questionsList.add(q3)
        return questionsList
    }
}
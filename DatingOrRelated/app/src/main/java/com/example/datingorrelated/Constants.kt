package com.example.datingorrelated

object Constants {
    fun getQuestions(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()


        val q1 = Question(1, "Zeus & Hera", R.drawable.zeus, R.drawable.hera, "Both")
        val q2 = Question(1, "Zeus & Metis", R.drawable.zeus, R.drawable.metis, "Dating")
        val q3 = Question(1, "Zeus & Hestia", R.drawable.zeus, R.drawable.hestia, "Related")

        val q4 = Question(1, "Gaia & Uranus", R.drawable.gaia, R.drawable.zeus, "Both")
        val q5 = Question(1, "Zeus & Rhea", R.drawable.zeus, R.drawable.rhea, "Both")
        val q6 = Question(1, "Chronos & Rhea", R.drawable.chronos, R.drawable.rhea, "Both")
        val q7 = Question(1, "Zeus & Demeter", R.drawable.zeus, R.drawable.demeter, "Both")


        val q8 = Question(1, "Zeus & Eris", R.drawable.zeus, R.drawable.eris, "Dating")
        val q9 = Question(1, "Zeus & Mnemosine", R.drawable.zeus, R.drawable.mnemosine, "Dating")
        val q11 = Question(1, "Zeus & Ganímedes", R.drawable.zeus, R.drawable.ganimede, "Dating")

        val q12 = Question(1, "Zeus & Poseidon", R.drawable.zeus, R.drawable.poseidon, "Related")
        val q13 = Question(1, "Hera & Hades", R.drawable.hera, R.drawable.hades, "Related")
        val q14 = Question(1, "Gaia & Eros", R.drawable.gaia, R.drawable.eros, "Related")

        questionsList.add(q1)
        questionsList.add(q2)
        questionsList.add(q3)
        questionsList.add(q4)
        questionsList.add(q5)
        questionsList.add(q6)
        questionsList.add(q7)
        questionsList.add(q8)
        questionsList.add(q9)
        questionsList.add(q11)
        questionsList.add(q12)
        questionsList.add(q13)
        questionsList.add(q14)


        return questionsList
    }
}
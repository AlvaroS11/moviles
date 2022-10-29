package com.example.datingorrelated

object Constants {
    fun getQuestions(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()


        val q1 = Question(1, "Zeus & Hera", R.drawable.zeus, R.drawable.hera, "Both")
        val q4 = Question(1, "Gaia & Uranus", R.drawable.gaia, R.drawable.zeus, "Both")
        val q5 = Question(1, "Zeus & Rhea", R.drawable.zeus, R.drawable.rhea, "Both")
        val q6 = Question(1, "Chronos & Rhea", R.drawable.chronos, R.drawable.rhea, "Both")
        val q7 = Question(1, "Zeus & Demeter", R.drawable.zeus, R.drawable.demeter, "Both")
        val q15 = Question(1, "Oceanus & Thetis", R.drawable.oceanus, R.drawable.thetis, "Both")
        val q16 = Question(1, "Deucalion & Pyrrha", R.drawable.deucalion, R.drawable.pyrrha, "Both")
        val q17 = Question(1, "Hades & Persephone", R.drawable.hades, R.drawable.persephone, "Both")



        val q2 = Question(1, "Zeus & Metis", R.drawable.zeus, R.drawable.metis, "Dating")
        val q8 = Question(1, "Zeus & Eris", R.drawable.zeus, R.drawable.eris, "Dating")
        val q9 = Question(1, "Zeus & Mnemosine", R.drawable.zeus, R.drawable.mnemosine, "Dating")
        val q11 = Question(1, "Zeus & Ganímedes", R.drawable.zeus, R.drawable.ganimede, "Dating")
        val q18 = Question(1, "Ares & Aphrodite", R.drawable.ares, R.drawable.aphrodite, "Dating")
        val q19 = Question(1, "Zeus & Leda", R.drawable.zeus, R.drawable.leda, "Dating")
        val q20 = Question(1, "Hyacinth & Apollo", R.drawable.hyacinthus, R.drawable.apollo, "Dating")
        val q21 = Question(1, "Zeus & Ganymede", R.drawable.zeus, R.drawable.ganymede, "Dating")
        val q22 = Question(1, "Eros & Psyche", R.drawable.zeus, R.drawable.psyche, "Dating")
        val q23 = Question(1, "Paris & Helen", R.drawable.paris, R.drawable.helen, "Dating")
        val q24 = Question(1, "Thetis & Pelios", R.drawable.thetis, R.drawable.pelios, "Dating")




        val q12 = Question(1, "Zeus & Poseidon", R.drawable.zeus, R.drawable.poseidon, "Related")
        val q13 = Question(1, "Hera & Hades", R.drawable.hera, R.drawable.hades, "Related")
        val q14 = Question(1, "Gaia & Eros", R.drawable.gaia, R.drawable.eros, "Related")
        val q3 = Question(1, "Zeus & Hestia", R.drawable.zeus, R.drawable.hestia, "Related")
        val q25 = Question(1, "Arthemis & Apollo", R.drawable.arthemis, R.drawable.apollo, "Related")
        val q26 = Question(1, "Nyx & Apollo", R.drawable.nyx, R.drawable.apollo, "Related")
        val q27 = Question(1, "Hestia & Hera", R.drawable.hestia, R.drawable.hera, "Related")
        val q28 = Question(1, "Dionysus & Athena", R.drawable.dionysus, R.drawable.athena, "Related")




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

        questionsList.add(q15)
        questionsList.add(q16)
        questionsList.add(q17)
        questionsList.add(q18)
        questionsList.add(q19)
        questionsList.add(q20)
        questionsList.add(q21)
        questionsList.add(q22)
        questionsList.add(q23)
        questionsList.add(q24)
        questionsList.add(q25)
        questionsList.add(q26)
        questionsList.add(q27)
        questionsList.add(q28)



        return questionsList
    }
}
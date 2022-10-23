package com.example.datingorrelated

sealed class Screen (val route: String){
    object MainScreen : Screen(route = "main_screen")
    object GameScreen : Screen(route = "game_screen/{name}"){
        fun createRoute(answerTime: Int) = "game_screen/$answerTime"
        fun createName(name: String) = "game_screen/$name"
    }
    object CreditsScreen : Screen(route = "credits_screen")
    object PauseScreen : Screen(route = "pause_screen")

    object EndGameScreen : Screen(route = "endGame_screen/{correct}/{name}/{time}"){
        fun createRoute(correct: Int, name:String, time: Int) = "endGame_screen/$correct/$name/$time"
    }
    object RankingScreen : Screen(route = "ranking_screen")

    object IntroName : Screen(route = "selectName"){
        fun nameRoute(name: String) = "selec"
    }
}
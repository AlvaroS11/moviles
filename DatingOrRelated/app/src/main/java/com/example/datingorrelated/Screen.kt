package com.example.datingorrelated

sealed class Screen (val route: String){
    object MainScreen : Screen(route = "main_screen")
    object GameScreen : Screen(route = "game_screen/{answerTime}"){
        fun createRoute(answerTime: Int) = "game_screen/$answerTime"
    }
    object CreditsScreen : Screen(route = "credits_screen")
    object PauseScreen : Screen(route = "pause_screen")

    object EndGameScreen : Screen(route = "endGame_screen/{correct}"){
        fun createRoute(correct: Int) = "endGame_screen/$correct"
    }
    object RankingScreen : Screen(route = "ranking_screen")
}
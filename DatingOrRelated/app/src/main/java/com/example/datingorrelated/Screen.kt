package com.example.datingorrelated

sealed class Screen (val route: String){
    object MainScreen : Screen("main_screen")
    object GameScreen : Screen("game_screen")
    object EndGameScreen : Screen(route = "endGame_screen")
    object RankingScreen : Screen(route = "ranking_screen")


}
package com.example.datingorrelated


import androidx.compose.runtime.*

import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(){
    val navController = rememberNavController();
    NavHost(navController = navController, startDestination = Screen.MainScreen.route){
        composable(route = Screen.MainScreen.route){
            MainScreen(navController = navController) //Asignamos la función a la ruta
        }
        composable(route = Screen.GameScreen.route){
            GameScreen()
        }
        composable(route = Screen.EndGameScreen.route){
            //EndGameScreen()
        }
        composable(route = Screen.RankingScreen.route){
            //Ranking()
        }
    }
}


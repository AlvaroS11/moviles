package com.example.datingorrelated

import androidx.compose.runtime.*

import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation(){
    val navController = rememberNavController();
    NavHost(navController = navController, startDestination = Screen.MainScreen.route){
        composable(route = Screen.MainScreen.route){
            MainScreen(navController = navController) //Asignamos la función a la ruta
        }
        composable(route = Screen.GameScreen.route){
            GameScreen(navController = navController)
        }
        composable(route = Screen.CreditsScreen.route){
            Credits(navController = navController)
        }
        composable(route = Screen.PauseScreen.route){
            Pause(navController = navController)
        }
        composable(route = Screen.EndGameScreen.route,
            arguments = listOf(
                navArgument("correct"){
                    type = NavType.IntType
                })
        ){ entry ->
            EndGameScreen(navController = navController, correct = entry.arguments!!.getInt("correct"))
        }
        composable(route = Screen.RankingScreen.route){
        }
    }
}


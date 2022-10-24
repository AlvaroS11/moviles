package com.example.datingorrelated

import androidx.compose.runtime.*

import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoadingScreen.route){
        composable(route = Screen.LoadingScreen.route){
            LoadingScreen(navController = navController)
        }

        composable(route = Screen.MainScreen.route){
            MainScreen(navController = navController)
        }

        composable(route =  Screen.IntroName.route){
            SelectName(navController = navController)
        }

        composable(route = Screen.CreditsScreen.route){
            Credits(navController = navController)
        }
        composable(route = Screen.PauseScreen.route){
            Pause(navController = navController)
        }
        composable(route = Screen.EndGameScreen.route,
            arguments = listOf(
                navArgument("name"){
                    type = NavType.StringType
                },
                navArgument("time"){
                    type = NavType.IntType
                },
                navArgument("correct") {
                    type = NavType.IntType
                }
            )
        ){ entry ->
            EndGameScreen(navController = navController,
                name = entry.arguments!!.getString("name").toString(),
                time = entry.arguments!!.getInt("time"),
                correct = entry.arguments!!.getInt("correct"),
            )
        }
        composable(route = Screen.RankingScreen.route){
            Ranking(navController = navController)
        }

        composable(route = Screen.GameScreen.route,
            arguments = listOf(
                navArgument("name"){
                type = NavType.StringType
            })
        ){   entry ->
                GameScreen(navController = navController, mapName = entry.arguments!!.getString("name").toString())
            }

    }
}


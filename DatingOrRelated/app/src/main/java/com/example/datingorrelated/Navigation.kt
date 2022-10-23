package com.example.datingorrelated

import androidx.compose.runtime.*

import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.datingorrelated.ui.theme.SelectName

@Composable
fun Navigation(){
    val navController = rememberNavController();
    NavHost(navController = navController, startDestination = Screen.MainScreen.route){
        composable(route = Screen.MainScreen.route){
            MainScreen(navController = navController) //Asignamos la función a la ruta
        }
       /* composable(route = Screen.GameScreen.route){
            //GameScreen(navController = navController)
            SelectName(navController = navController)

        }
        */

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
                navArgument("correct") {
                    type = NavType.IntType
                }, navArgument("name"){
                    type = NavType.StringType
                },
                navArgument("time"){
                    type = NavType.IntType
                }
            )
        ){ entry ->
            EndGameScreen(navController = navController,
                correct = entry.arguments!!.getInt("correct"),
                name = entry.arguments!!.getString("name").toString(),
                time = entry.arguments!!.getInt("time")
            )
        }
        composable(route = Screen.RankingScreen.route){
            Ranking(navController = navController)
        }

        composable(route = Screen.GameScreen.route,
            arguments = listOf(
                navArgument("name"){
                type = NavType.StringType
                //defaultValue = "Empty Name"
                //nullable = false
            })
        ){   entry ->
                GameScreen(navController = navController, mapName = entry.arguments!!.getString("name").toString())
            }

    }
}


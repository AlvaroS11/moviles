package com.example.datingorrelated

import android.app.Activity
import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController


@Composable
fun EndGameScreen(navController: NavController, name: String,time: Int, correct: Int) {
    println("CORRECT ANSWERS: " + correct)
    save(name = name, time = time, score = correct)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Fin de la partida!",
            fontSize = 32.sp,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Estos son tus resultados $name : $correct aciertos!",
            fontSize = 24.sp,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )
        Text(
            text = "En $time segundos",
            fontSize = 24.sp,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )
        Button(
            onClick = {
//                save(name = name, time = time)
                navController.navigate(Screen.MainScreen.route);
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)
        ) {
            Text(
                text = "Volver a inicio",
                fontSize = 32.sp,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        val activity = (LocalContext.current as? Activity)

        Button(
            onClick = {
                activity?.finish()
            },
        ) {
            Text(
                text = "Exit",
                fontSize = 24.sp,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun save(name: String, time: Int, score: Int){
    val owner = LocalViewModelStoreOwner.current
    owner?.let {
        val viewModel: GameViewModel = viewModel(
            it,
            "GameViewModel",
            GameViewModelFactory(
                LocalContext.current.applicationContext
                        as Application
            )
        )
        viewModel.insertProduct(
            GameStats(
                name,
                time,
                score
            )
        )
    }
}
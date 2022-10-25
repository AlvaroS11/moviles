package com.example.datingorrelated

import android.app.Activity
import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
            .fillMaxHeight()
            .background(MaterialTheme.colors.background),
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
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = "Estos son tus resultados $name :",
            fontSize = 24.sp,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = "$correct aciertos",
            fontSize = 24.sp,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = "en $time segundos",
            fontSize = 24.sp,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(10.dp))
        Button(
            onClick = {
//                save(name = name, time = time)
                navController.navigate(Screen.MainScreen.route);
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
        ) {
            Text(
                text = "Volver a inicio",
                fontSize = 32.sp,
                color = MaterialTheme.colors.background,
                modifier = Modifier.width(250.dp),
                textAlign = TextAlign.Center
            )
        }
        val activity = (LocalContext.current as? Activity)
        Spacer(modifier = Modifier.size(10.dp))
        Button(
            onClick = {
                activity?.finish()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
        ) {
            Text(
                text = "Exit",
                fontSize = 24.sp,
                color = MaterialTheme.colors.error,
                modifier = Modifier.width(100.dp),
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
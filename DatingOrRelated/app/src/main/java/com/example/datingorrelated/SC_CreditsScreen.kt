package com.example.datingorrelated


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

import androidx.navigation.NavController

@Composable
fun Credits(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Credits :)",
            fontSize = 32.sp,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Guillermo Juan García-Delgado Álvarez \n Álvaro García Sierra \n Aurora García Raigón\n  Cecilia Garrido Cano",
            fontSize = 26.sp,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = "DADM · Diseño y Desarrollo de Videojuegos \n URJC 2022",
            fontSize = 22.sp,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Button(
            onClick = {//CAMBIAR ESTO PARA HACER QUE VUELVA A DONDE ESTABA
                navController.navigate(Screen.MainScreen.route);
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)
        ) {
            Text(
                text = "Back",
                fontSize = 32.sp,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

    }
}


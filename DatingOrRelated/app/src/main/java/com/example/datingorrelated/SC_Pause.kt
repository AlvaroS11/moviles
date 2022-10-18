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
fun Pause(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text( //title text
            text = "Pause",
            fontSize = 32.sp,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        /*--------------------------------------- BUTTONS ---------------------------------------*/
        //Continue button
        Button(
            onClick = {
                navController.navigate(Screen.GameScreen.route);
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)
        ) {
            Text(
                text = "Continue",
                fontSize = 32.sp,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        //Credits button
        Button(
            onClick = {
                navController.navigate(Screen.CreditsScreen.route);
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)
        ) {
            Text(
                text = "Credits",
                fontSize = 24.sp,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        //Exit button
        Button(
            onClick = {
                navController.navigate(Screen.MainScreen.route);
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)
        ) {
            Text(
                text = "Exit",
                fontSize = 24.sp,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

    }
}


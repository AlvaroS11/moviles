package com.example.datingorrelated

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

    @Composable
    fun SelectName(navController: NavController) {
            IntroduceName(navController)
    }

    @Composable
    fun IntroduceName(navController: NavController) {
        var gameName by remember { mutableStateOf("") }

        val onProductTextChange = { text: String ->
            gameName = text
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CustomTextField(
                title = "Player Name",
                textState = gameName,
                onTextChange = onProductTextChange,
                keyboardType = KeyboardType.Text
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                var buttonPlay = "Accept"
                Button(onClick = {
                    if (gameName.isNotEmpty()) {
                            navController.navigate(Screen.GameScreen.createRoute(gameName))
                    }
                    else{//To do
                        buttonPlay = "Please, introduce a name"
                     }
                })
                    {
                        Text(buttonPlay)
                    }
            }
        }
    }

package com.example.datingorrelated

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
    fun SelectName(navController: NavController) {
        Column(){
            Row(modifier = Modifier.background(MaterialTheme.colors.background)){
                IconButton(onClick = {
                    navController.navigate(Screen.MainScreen.route)
                }){
                    Icon(imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Menu icon",
                        tint = MaterialTheme.colors.onBackground)
                }
            }

            IntroduceName(navController)
        }
    }

    @Composable
    fun IntroduceName(navController: NavController) {
        var gameName by remember { mutableStateOf("") }

        val onProductTextChange = { text: String ->
            gameName = text
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(MaterialTheme.colors.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
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

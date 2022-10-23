package com.example.datingorrelated.ui.theme

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.datingorrelated.*


    @Composable
    fun SelectName(navController: NavController) {

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

            introduceName(viewModel, navController)
        }
    }


    @Composable
    fun introduceName(viewModel: GameViewModel, navController: NavController) {
        var gameName by remember { mutableStateOf("") }
        var gameSecs by remember { mutableStateOf("") }
        var searching by remember { mutableStateOf(false) }

        val onProductTextChange = { text: String ->
            gameName = text
        }

        val ongameSecsTextChange = { text: String ->
            gameSecs = text
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
                /* Button(onClick = {
                    if (gameName.isNotEmpty()) {
                        viewModel.insertProduct(
                            GameStats(
                                gameName,
                                gameSecs.toInt()
                            )
                        )
                        searching = false
                    } else {
                        println("JUST FOR DEBUG!!__SAVING WITHOUT SECONDS")
                        viewModel.insertProduct(
                            GameStats(
                                gameName,
                                0
                            )
                        )
                        searching = false
                    }
                }) {
                    Text("Add")
                }


            }
        }*/
                var buttonPlay = "Aceptar"
                Button(onClick = {
                    if (gameName.isNotEmpty()) {
                            navController.navigate(Screen.GameScreen.createName(gameName))
                            println("pressed not empty" + gameName)
                    }
                    else{//To do
                        buttonPlay = "Rellenar campo"
                     }
                })
                    {
                        Text(buttonPlay)
                    }


            }
        }
    }

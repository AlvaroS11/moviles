package com.example.datingorrelated

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.media.MediaPlayer
import android.provider.MediaStore.Audio.Media
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

var answerTime = 20

//region ---------- OPTIONS MENU REGION ----------
@Composable
fun TopBar(scope: CoroutineScope, scaffoldState: ScaffoldState){
    TopAppBar (
        //modifier = Modifier.background(MaterialTheme.colors.primary),
        title = {Text(text = "Dating or Related",
            color = MaterialTheme.colors.onPrimary)},
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }){
                Icon(imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu icon")
            }
        }
    )
}

@Composable
fun RaddioButtonTimeDifficulty(scope: CoroutineScope, context: Context, dataStore: StoreUserSettings) {
    val radioOptions = listOf("Easy", "Normal", "Difficult")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions.first()) }
    Column (modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,){
        Text(text = "Time difficulty", modifier = Modifier.padding(bottom = 16.dp))
        radioOptions.forEach { text ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) }
                    )
                    .padding(horizontal = 16.dp)
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = {
                        onOptionSelected(text)
                        when (text){
                            "Easy" -> {
                                answerTime = 20
                            }
                            "Normal" -> {
                                answerTime = 10
                            }
                            "Difficult" -> {
                                answerTime = 5
                            }
                        }

                        scope.launch{
                            dataStore.saveQuestionTime(answerTime.toString())
                        }
                    }
                )
                Text(
                    text = text,
                    modifier = Modifier.padding(start = 16.dp)
                )

            }
        }
    }
}

@Composable
fun HandleMainTheme(mediaPlayer: MediaPlayer){
    var sliderPosition by remember { mutableStateOf(1f) }
    Text(text = (sliderPosition*100).toInt().toString())
    Slider(value = sliderPosition,
        valueRange = 0f..1f,
        modifier = Modifier.padding(horizontal = 16.dp),
        onValueChange = {sliderPosition = it})
    mediaPlayer.setVolume(sliderPosition, sliderPosition)
}

@Composable
fun HandleDarkTheme(){
    var theme = isSystemInDarkTheme()
    TextButton(onClick = {
        if (theme) Configuration.UI_MODE_NIGHT_NO
        else Configuration.UI_MODE_NIGHT_YES
    }){
        Text(text = "Night/Light mode",
            modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun Drawer(scope: CoroutineScope, context: Context, dataStore: StoreUserSettings){
    Column {
        HandleDarkTheme()
        //HandleMainTheme(mediaPlayer)
        RaddioButtonTimeDifficulty(scope, context, dataStore)
    }
}
//endregion

@Composable
fun MainScreen(navController: NavController) {
    // Scaffold is a layout which implements the basic material design layout structure
    // With it we can add a top bar and a drawer for the options menu

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val dataStore = StoreUserSettings(context)

    val answerTimeString = dataStore.getQuestionTime.collectAsState(initial = "")
    if(answerTimeString.value!! != ""){
        answerTime = answerTimeString.value!!.toInt()
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {TopBar(scope, scaffoldState)},
        content = {MainScreenContent(navController = navController)},
        drawerContent = {Drawer(scope, context, dataStore)}

    )
}

//region --------- MAIN MENU CONTENT (buttons, etc..) ------------
@Composable
fun MainScreenContent(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Time: "+ answerTime)
        Text(
            text = "Dating or related?",
            fontSize = 32.sp,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Button(
            onClick = {
                navController.navigate(Screen.GameScreen.route);
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)
        ) {
            Text(
                text = "Start Game!",
                fontSize = 32.sp,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Button(
            onClick = {
                navController.navigate(Screen.CreditsScreen.route);
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)
        ) {
            Text(
                text = "Credits",
                fontSize = 32.sp,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }


        Button(
            onClick = {
                navController.navigate(Screen.RankingScreen.route);
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)
        ) {
            Text(
                text = "Ranking",
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

//endregion

package com.example.datingorrelated

import android.app.Activity
import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// GLOBAL VARIABLES
var preferredTheme = "" // by default it will be the system theme, otherwise it can be "Dark" or "Light"
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
                Icon(imageVector = Icons.Filled.Settings,
                    contentDescription = "Menu icon")
            }
        }
    )
}

@Composable
fun RadioButtonTimeDifficulty(scope: CoroutineScope, context: Context, dataStore: StoreUserSettings) {
    val radioOptions = listOf("Easy: 20 seconds", "Normal: 10 seconds", "Difficult: 5 seconds")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions.first()) }
    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Time difficulty (seconds per question)", modifier = Modifier.padding(bottom = 16.dp))
        radioOptions.forEach { text ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = {
                        onOptionSelected(text)
                        when (text){
                            radioOptions[0] -> { // easy
                                answerTime = 20
                            }
                            radioOptions[1] -> { // normal
                                answerTime = 10
                            }
                            radioOptions[2] -> { // difficult
                                answerTime = 5
                            }
                        }
                        // store the answer time on device
                        scope.launch{
                            dataStore.saveQuestionTime(answerTime.toString())
                        }
                    }
                )
                Text( // radio button text
                    text = text,
                    modifier = Modifier.align(alignment = Alignment.CenterVertically)
                )
            }
        }
    }
}


@Composable
fun RadioButtonHandleTheme(scope: CoroutineScope, context: Context, dataStore: StoreUserSettings) {
    val radioOptions = listOf("Dark", "Light", "Same as system")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions.first()) }
    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Light mode theme", modifier = Modifier.padding(bottom = 16.dp))
        radioOptions.forEach { text ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = {
                        onOptionSelected(text)
                        when (text){
                            radioOptions[0] -> { // easy
                                preferredTheme = text
                            }
                            radioOptions[1] -> { // normal
                                preferredTheme = text
                            }
                            radioOptions[2] -> { // difficult
                                preferredTheme = text
                            }
                        }
                        // store the answer time on device
                        scope.launch{
                            dataStore.savePreferredTheme(preferredTheme.toString())
                        }
                    }
                )
                Text( // radio button text
                    text = text,
                    modifier = Modifier.align(alignment = Alignment.CenterVertically)
                )
            }
        }
    }
}

@Composable
fun HandleVolume(mediaPlayer: MediaPlayer){
    var sliderPosition by remember { mutableStateOf(1f) }
    Text(text = (sliderPosition*100).toInt().toString())
    Slider(value = sliderPosition,
        valueRange = 0f..1f,
        modifier = Modifier.padding(horizontal = 16.dp),
        onValueChange = {sliderPosition = it})
    mediaPlayer.setVolume(sliderPosition, sliderPosition)
}

@Composable
fun Drawer(scope: CoroutineScope, context: Context, dataStore: StoreUserSettings){
    Column (modifier = Modifier.background(MaterialTheme.colors.background).fillMaxHeight()) {
        //HandleVolume()
        RadioButtonHandleTheme(scope, context, dataStore)
        RadioButtonTimeDifficulty(scope, context, dataStore)
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
            .fillMaxHeight()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Time: "+ answerTime)
        Text(
            text = "Dating or related?",
            fontSize = 32.sp,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Button(
            onClick = {
                navController.navigate(Screen.GameScreen.route);
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
        ) {
            Text(
                text = "Start Game!",
                fontSize = 32.sp,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Button(
            onClick = {
                navController.navigate(Screen.CreditsScreen.route);
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
        ) {
            Text(
                text = "Credits",
                fontSize = 32.sp,
                color = MaterialTheme.colors.onPrimary,
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

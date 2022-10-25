package com.example.datingorrelated

import android.app.Activity
import android.media.MediaPlayer
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
var volume = 1f

//region ---------- OPTIONS MENU REGION ----------
@Composable
fun TopBar(scope: CoroutineScope, scaffoldState: ScaffoldState){
    TopAppBar (
        modifier = Modifier.background(MaterialTheme.colors.primary),
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
fun RadioButtonTimeDifficulty(scope: CoroutineScope, dataStore: StoreUserSettings) {
    val radioOptions = listOf("Easy: 20 seconds", "Normal: 10 seconds", "Difficult: 5 seconds")

    val optionNumber: Int = if(answerTime == 20){
        0
    } else{
        if (answerTime == 10) {
            1
        } else 2
    }

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[optionNumber]) }
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
fun RadioButtonHandleTheme(scope: CoroutineScope, dataStore: StoreUserSettings) {
    val radioOptions = listOf("Dark", "Light", "Same as system")

    val optionNumber: Int = if(preferredTheme == "Dark"){
        0
    } else{
        if (preferredTheme == "Light") {
            1
        } else 2
    }

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[optionNumber]) }
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
                            radioOptions[0] -> { // dark
                                preferredTheme = text
                            }
                            radioOptions[1] -> { // light
                                preferredTheme = text
                            }
                            radioOptions[2] -> { //
                                preferredTheme = text
                            }
                        }
                        // store the answer time on device
                        scope.launch{
                            dataStore.savePreferredTheme(preferredTheme)
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
fun HandleVolume(scope: CoroutineScope, dataStore: StoreUserSettings){

    var sliderPosition by rememberSaveable { mutableStateOf(volume) }

    Text(
        modifier = Modifier.padding(horizontal = 16.dp),
        text = "Volume: "+ (sliderPosition*100).toInt().toString()
    )

    Slider(value = sliderPosition,
        valueRange = 0f..1f,
        modifier = Modifier.padding(horizontal = 16.dp),
        onValueChange = {
            sliderPosition = it
            volume = sliderPosition
            scope.launch{
                dataStore.saveVolume(volume.toString())
            }
        })
}

@Composable
fun Drawer(scope: CoroutineScope, dataStore: StoreUserSettings){
    LazyColumn (modifier = Modifier
        .background(MaterialTheme.colors.background)
        .fillMaxHeight()) {
        item{
            Text(modifier = Modifier.padding(horizontal = 16.dp),
                text = "Settings",
                fontSize = 30.sp,
                color = MaterialTheme.colors.onBackground
                )
            HandleVolume(scope, dataStore)
            RadioButtonHandleTheme(scope, dataStore)
            RadioButtonTimeDifficulty(scope, dataStore)
        }
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

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {TopBar(scope, scaffoldState)},
        content = {MainScreenContent(navController = navController)},
        drawerContent = {Drawer(scope, dataStore)}
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
        Text(
            text = "Dating or related?",
            fontSize = 32.sp,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(10.dp))
        Button(
            onClick = {
                navController.navigate(Screen.IntroName.route)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
        ) {
            Text(
                text = "Start Game!",
                fontSize = 32.sp,
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.Center
            )

        }
        Spacer(modifier = Modifier.size(10.dp))
        Button(
            onClick = {
                navController.navigate(Screen.CreditsScreen.route)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
        ) {
            Text(
                text = "Credits",
                fontSize = 32.sp,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.width(150.dp),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.size(10.dp))

        Button(
            onClick = {
                navController.navigate(Screen.RankingScreen.route)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
        ) {
            Text(
                text = "Ranking",
                fontSize = 32.sp,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.width(150.dp),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.size(10.dp))

        val activity = (LocalContext.current as? Activity)

        Button(
            onClick = {
                activity?.finish()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onPrimary)
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

//endregion

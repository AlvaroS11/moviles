package com.example.datingorrelated

import android.app.Activity
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
fun Drawer(){
    var theme = isSystemInDarkTheme()
    Column(){
        TextButton(onClick = {
            if (theme) Configuration.UI_MODE_NIGHT_NO
            else Configuration.UI_MODE_NIGHT_YES
        }){
            Text(text = "Night/Light mode",
                modifier = Modifier.fillMaxWidth())
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

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {TopBar(scope, scaffoldState)},
        content = {MainScreenContent(navController = navController)},
        drawerContent = {Drawer()}
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

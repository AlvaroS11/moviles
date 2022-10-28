package com.example.datingorrelated

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoadingScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colors.secondary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.zeus),
            contentDescription = null,
            modifier = Modifier
                .size(250.dp)
                .clip(CircleShape)
                .border(10.dp, MaterialTheme.colors.primary, CircleShape)
        )
        Spacer(modifier = Modifier.size(30.dp))
        Button(
            onClick = {
                navController.navigate(Screen.MainScreen.route)
            }
        ) {
            Text(
            text = "DATING OR RELATED",
            fontSize = 32.sp,
            color = Color.White,
            textAlign = TextAlign.Center
            )

        }
        Text(
            text = "Press the tittle to start",
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            textAlign = TextAlign.Center
        )
    }
}
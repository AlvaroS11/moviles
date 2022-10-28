package com.example.datingorrelated.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Peach2,
    primaryVariant = Peach1,
    secondary = Teal1,
    secondaryVariant = Teal2,
    surface = Red200,
    onPrimary = Color.Black,
    background = Color.Black,
    onBackground = Color.White

)

private val LightColorPalette = lightColors(
    primary = Peach2,
    primaryVariant = Peach1,
    secondary = Teal1,
    secondaryVariant = Teal2,
    surface = Red200,
    background = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

@Composable
fun DatingOrRelatedTheme(
    preferredTheme: String,
    content: @Composable () -> Unit
) {
    val colors = if (preferredTheme == "Dark") {
        DarkColorPalette
    } else {
        if (preferredTheme == "Light") {
            LightColorPalette
        }else{
            if (isSystemInDarkTheme()){
                DarkColorPalette
            } else{
                LightColorPalette
            }
        }
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
package com.example.datingorrelated

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.example.datingorrelated.ui.theme.DatingOrRelatedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // get preferred light mode theme from device storage
            val context = LocalContext.current
            val dataStore = StoreUserSettings(context)
            preferredTheme = dataStore.getPreferredTheme.collectAsState(initial = "").value

            // get answer time from device storage
            val answerTimeString = dataStore.getQuestionTime.collectAsState(initial = "")
            if(answerTimeString.value != ""){
                answerTime = answerTimeString.value.toInt()
            }
            // get volume from device storage
            val volumeSfxString = dataStore.getVolumeSoundEffects.collectAsState(initial = "")
            if(volumeSfxString.value != ""){
                soundEffectsVolume = volumeSfxString.value.toFloat()
            }
            val volumeString = dataStore.getVolumeMainTheme.collectAsState(initial = "")
            if(volumeString.value != ""){
                mainThemeVolume = volumeString.value.toFloat()
            }
            // get if show answer from device storage
            val showAnswerString = dataStore.getShowAnswer.collectAsState(initial = "")
            if(showAnswerString.value != ""){
                showAnswer = showAnswerString.value.toBoolean()
            }


            // start navigation, give theme
            DatingOrRelatedTheme(preferredTheme = preferredTheme, content = { // set theme
                Navigation()
            })
        }
    }
}



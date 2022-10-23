package com.example.datingorrelated

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.example.datingorrelated.ui.theme.DatingOrRelatedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // get preferred light mode theme from device storage
            val context = LocalContext.current
            val dataStore = StoreUserSettings(context)
            preferredTheme = dataStore.getPreferredTheme.collectAsState(initial = "").value!!

            DatingOrRelatedTheme(preferredTheme = preferredTheme, content = { // set theme
                Navigation()
            })

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
            }
        }
    }
}



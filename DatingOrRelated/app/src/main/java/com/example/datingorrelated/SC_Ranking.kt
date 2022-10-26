package com.example.datingorrelated

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController


@Composable
fun Ranking(navController: NavController) {

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

        RankingSetUp(viewModel, navController = navController)
    }
}


@Composable
fun RankingSetUp(viewModel: GameViewModel, navController: NavController) {

    val allGames by viewModel.allGames.observeAsState(listOf())
    val searchResults by viewModel.searchResults.observeAsState(listOf())

    Ranking(
        allGames = allGames ,
        searchResults = searchResults ,
        viewModel = viewModel,
        navController = navController
    )
}

@Composable
fun Ranking(allGames: List<GameStats>, searchResults: List<GameStats>, viewModel: GameViewModel, navController: NavController) {
    var gameName by remember { mutableStateOf("") }
    var scorePoints by remember { mutableStateOf("") }
    var gameSecs by remember { mutableStateOf("") }
    var searching by remember { mutableStateOf(false) }

    val onProductTextChange = { text: String ->
        gameName = text
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Top,
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
                .padding(1.dp)
        ) {
            Button(onClick = {
                searching = true
                viewModel.findProduct(gameName)
            }) {
                Text("Search")
            }

            Button(onClick = {
                searching = false
                viewModel.deleteProduct(gameName)
            }) {
                Text("Delete")
            }

            Button(onClick = {
                searching = false
                gameName = ""
                gameSecs = ""
            }) {
                Text("Clear")
            }
        }

        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(MaterialTheme.colors.secondary)
        ) {
            val list = if (searching) searchResults else allGames

            item {
                TitleRow(head1 = "Id", head2 = "Player Name", head3 = "Time", head4 = "Score")
            }

            items(list) { game ->
                GameRow(
                    id = game.id, name = game.playerName,
                    gameSecs = game.timeSecs, scorePoints = game.score
                )
            }
        }
        Button(
            onClick = {
                navController.navigate(Screen.MainScreen.route)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
        ) {
            Text(
                text = "Back To Menu",
                fontSize = 32.sp,
                color = MaterialTheme.colors.background,
                modifier = Modifier.width(250.dp),
                textAlign = TextAlign.Center,

                )
        }
    }


}

@Composable
fun TitleRow(head1: String, head2: String, head3: String, head4: String) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(head1, color = Color.White,
            modifier = Modifier
                .weight(0.1f))
        Text(head2, color = Color.White,
            modifier = Modifier
                .weight(0.2f))
        Text(head3, color = Color.White,
            modifier = Modifier.weight(0.2f))
        Text(head4, color = Color.White,
            modifier = Modifier.weight(0.2f))
    }
}

@Composable
fun GameRow(id: Int, name: String, gameSecs: Int, scorePoints: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(id.toString(), modifier = Modifier
            .weight(0.1f))
        Text(name, modifier = Modifier.weight(0.2f))
        Text(gameSecs.toString(), modifier = Modifier.weight(0.2f))
        Text(scorePoints.toString(), modifier = Modifier.weight(0.2f))
    }
}

@Composable
fun CustomTextField(
    title: String,
    textState: String,
    onTextChange: (String) -> Unit,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = textState,
        onValueChange = { onTextChange(it) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        singleLine = true,
        label = { Text(title) },
        modifier = Modifier.padding(10.dp),
        textStyle = TextStyle(fontWeight = FontWeight.Bold,
            fontSize = 30.sp)
    )
}

class GameViewModelFactory(val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GameViewModel(application) as T
    }
}


package com.example.datingorrelated

import android.app.Application
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
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
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner.current
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

        /*CustomTextField(
            title = "Score",
            textState = gameSecs,
            onTextChange = ongameSecsTextChange,
            keyboardType = KeyboardType.Number
        )
*/
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Button(onClick = {
                if (gameSecs.isNotEmpty()) {
                    viewModel.insertProduct(
                        GameStats(
                            gameName,
                            gameSecs.toInt()
                        )
                    )
                    searching = false
                }
                else{
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
        ) {
            val list = if (searching) searchResults else allGames

            item {
                TitleRow(head1 = "Id", head2 = "Player Name", head3 = "Score")
            }

            items(list) { game ->
                GameRow(
                    id = game.id, name = game.playerName,
                    gameSecs = game.timeSecs
                )
            }
        }
    }
    Button(
        onClick = {//CAMBIAR ESTO PARA HACER QUE VUELVA A DONDE ESTABA
            navController.navigate(Screen.MainScreen.route);
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)
    ) {
        Text(
            text = "Back To Menu",
            fontSize = 32.sp,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }

}

@Composable
fun TitleRow(head1: String, head2: String, head3: String) {
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
    }
}

@Composable
fun GameRow(id: Int, name: String, gameSecs: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(id.toString(), modifier = Modifier
            .weight(0.1f))
        Text(name, modifier = Modifier.weight(0.2f))
        Text(gameSecs.toString(), modifier = Modifier.weight(0.2f))
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


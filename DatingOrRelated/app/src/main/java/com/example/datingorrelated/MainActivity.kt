package com.example.datingorrelated

import android.app.Application
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColor
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.datingorrelated.Constants
import com.example.datingorrelated.Question
import kotlinx.coroutines.delay
import kotlin.math.min
import kotlin.random.Random
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlin.time.Duration.Companion.seconds

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()


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

// ---------- GAME SCREEN -----------

//region GameScreen Global Variables

data class Response(var _datingBtnColor: Int, var _relatedBtnColor: Int, var _bothBtnColor: Int, var _correct: Int)

var mQuestionsList = Constants.getQuestions()
var suffledQuestions = mQuestionsList.shuffled()

//endregion
var testingSeconds = 0

@Composable
fun GameScreen(navController: NavController) {
    var primaryColor = MaterialTheme.colors.primary

    var mCurrentPosition by rememberSaveable { mutableStateOf(0) }

    var canClick by rememberSaveable { mutableStateOf(true) }
    var correct by rememberSaveable { mutableStateOf(0) }

    var datingBtnColor by rememberSaveable { mutableStateOf(primaryColor.toArgb())}
    var relatedBtnColor by rememberSaveable { mutableStateOf(primaryColor.toArgb())}
    var bothBtnColor by rememberSaveable { mutableStateOf(primaryColor.toArgb())}

    val question = suffledQuestions!![mCurrentPosition]
    val maximum = mQuestionsList.size

    var answerSeconds = 10
    var secondsToDisappear by rememberSaveable { mutableStateOf(answerSeconds) }

    //var min by rememberSaveable { mutableStateOf(0) }
    var sec by rememberSaveable { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //timer()
        //print(sec)
        TopLevel()
        //handleTime(min, sec)
        CorrectAnswersText(number = correct)
        QuestionText(question.question)
        Row( // Images
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            QuestionImage(question.firstImage)
            QuestionImage(question.secondImage)
        }
        // Dating button
        Button(
            onClick = {
                if (canClick){
                    canClick = false // it has just clicked, so it can't click again

                    secondsToDisappear = answerSeconds // the user has answered, rewind timer

                    print(testingSeconds)

                    val(_datingBtnColor, _relatedBtnColor, _bothBtnColor, _correct) = handleResponse("Dating", question, correct)

                    // reasign colors based on answer
                    datingBtnColor = _datingBtnColor
                    relatedBtnColor = _relatedBtnColor
                    bothBtnColor = _bothBtnColor
                    correct = _correct

                    Handler(Looper.getMainLooper()).postDelayed({
                        canClick = true // when the time passes, we go to the next question, so user is able to click again

                        // change color back to default
                        datingBtnColor = primaryColor.toArgb()
                        relatedBtnColor = primaryColor.toArgb()
                        bothBtnColor = primaryColor.toArgb()

                        if(mCurrentPosition == maximum-1){
                            navController.navigate(Screen.EndGameScreen.route) // go to end screen
                        }else{
                            mCurrentPosition++ // go to next question
                        }
                    }, 500)
                }

            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(datingBtnColor))
        ) {
            ButtonText("Dating")
        }
        // Related button
        Button(
            onClick = {
                if (canClick){
                    canClick = false // it has just clicked, so it can't click again

                    secondsToDisappear = answerSeconds // the user has answered, rewind timer

                    print(testingSeconds)

                    val(_datingBtnColor, _relatedBtnColor, _bothBtnColor, _correct) = handleResponse("Related", question, correct)

                    // reasign colors based on answer
                    datingBtnColor = _datingBtnColor
                    relatedBtnColor = _relatedBtnColor
                    bothBtnColor = _bothBtnColor
                    correct = _correct

                    Handler(Looper.getMainLooper()).postDelayed({
                        canClick = true // when the time passes, we go to the next question, so user is able to click again

                        // change color back to default
                        datingBtnColor = primaryColor.toArgb()
                        relatedBtnColor = primaryColor.toArgb()
                        bothBtnColor = primaryColor.toArgb()

                        if(mCurrentPosition == maximum-1){
                            navController.navigate(Screen.EndGameScreen.route) // go to end screen
                        }else{
                            mCurrentPosition++ // go to next question
                        }
                    }, 500)
                }

            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(relatedBtnColor))
        ) {
            ButtonText("Related")
        }
        // Both button
        Button(
            onClick = {
                if (canClick){
                    canClick = false // it has just clicked, so it can't click again

                    secondsToDisappear = answerSeconds // the user has answered, rewind timer

                    print(testingSeconds)

                    val(_datingBtnColor, _relatedBtnColor, _bothBtnColor, _correct) = handleResponse("Both", question, correct)

                    // reasign colors based on answer
                    datingBtnColor = _datingBtnColor
                    relatedBtnColor = _relatedBtnColor
                    bothBtnColor = _bothBtnColor
                    correct = _correct

                    Handler(Looper.getMainLooper()).postDelayed({
                        canClick = true // when the time passes, we go to the next question, so user is able to click again

                        // change color back to default
                        datingBtnColor = primaryColor.toArgb()
                        relatedBtnColor = primaryColor.toArgb()
                        bothBtnColor = primaryColor.toArgb()

                        if(mCurrentPosition == maximum-1){
                            navController.navigate(Screen.EndGameScreen.route) // go to end screen
                        }else{
                            mCurrentPosition++ // go to next question
                        }
                    }, 500)
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(bothBtnColor))
        ) {
            ButtonText("Both")
        }

        TimerText(secondsToDisappear)

    } // end column

    // To manage the answer time
    LaunchedEffect(Unit) {
        while(true){
            while (secondsToDisappear >= 0) {
                delay(1.seconds)
                secondsToDisappear -= 1
            }
            // time has passed and the user hasn't responded, go to next question
            mCurrentPosition++
            secondsToDisappear = answerSeconds
        }
    }
}

//region Questions related methods
fun handleResponse(button: String, question: Question, correct: Int): Response{
    var _datingBtnColor = 0
    var _bothBtnColor = 0
    var _relatedBtnColor = 0
    var _correct = correct

    println("printing test")
    when(button){
        "Dating" -> when(question!!.answer){
            "Dating" -> {
                _datingBtnColor = Color.Green.toArgb()
                _correct++
            }
            "Both" -> {
                _bothBtnColor = Color.Green.toArgb()
                _datingBtnColor = Color.Red.toArgb()
            }
            "Related" -> {
                _relatedBtnColor = Color.Green.toArgb()
                _datingBtnColor = Color.Red.toArgb()

            }
        }
        "Both" -> when(question!!.answer){
            "Dating" -> {
                _bothBtnColor = Color.Red.toArgb()
                _datingBtnColor = Color.Green.toArgb()
            }
            "Both" -> {
                _bothBtnColor = Color.Green.toArgb()
                _correct++
            }
            "Related" -> {
                _bothBtnColor = Color.Red.toArgb()
                _relatedBtnColor = Color.Green.toArgb()
            }
        }
        "Related" -> when(question!!.answer){
            "Dating" -> {
                _datingBtnColor = Color.Green.toArgb()
                _relatedBtnColor = Color.Red.toArgb()
            }
            "Both" -> {
                _bothBtnColor = Color.Green.toArgb()
                _relatedBtnColor = Color.Red.toArgb()
            }
            "Related" -> {
                _relatedBtnColor = Color.Green.toArgb()
                _correct++
            }
        }
    }

    return Response(_datingBtnColor, _relatedBtnColor, _bothBtnColor, _correct)
}

@Composable
fun TimerText(number: Int){
    var color = MaterialTheme.colors.primary
    if (number == 0){
        color = Color.Red
    }

    Text(
        text = "TimeLeft: $number",
        fontSize = 24.sp,
        color = color,
        textAlign = TextAlign.Center
    )
}

@Composable
fun CorrectAnswersText(number: Int){
    Text(
        text = "Correct answers: $number",
        fontSize = 24.sp,
        color = MaterialTheme.colors.primary,
        textAlign = TextAlign.Center
    )
}

@Composable
fun ButtonText(name: String) {
    Text(
        text = "$name",
        fontSize = 32.sp,
        color = MaterialTheme.colors.onPrimary,
        textAlign = TextAlign.Center
    )
}

@Composable
fun QuestionText(question: String) {
    Text(
        text = "$question",
        fontSize = 32.sp,
        color = MaterialTheme.colors.onPrimary,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
fun QuestionImage(image: Int) {
    Image(
        painter = painterResource(image),
        contentDescription = "Contact profile picture",
        modifier = Modifier
            // Set image size to 40 dp
            .size(150.dp)
    )
}

//endregion

//region Timer related methods
@Preview
@Composable
fun timer() {
    var seconds by remember {
        mutableStateOf(0)
    }
    var minutes by remember {
        mutableStateOf(0)
    }
    Text(
        text = "0 $minutes : $seconds",
        fontSize = 40.sp,
        color = MaterialTheme.colors.primary,
        textAlign = TextAlign.Center
    )

    LaunchedEffect(key1 = seconds, key2 = minutes){
        delay(1000)
        if(seconds % 59 == 0 && seconds != 0){
            seconds = 0
            minutes = minutes + 1
        }
        else
            seconds = seconds + 1
    }
    println(seconds)
}


class ViewModelTimer : ViewModel(){
    var totalMinutos by mutableStateOf(0)
    var totalSegundos by mutableStateOf(0)

    @Composable
    fun addingTime() {
        LaunchedEffect(key1 = totalMinutos, key2 =  totalSegundos){
            delay(1000)
            if (totalMinutos % 59 == 0 && totalSegundos != 0) {
                totalSegundos = 0
                totalMinutos = totalMinutos + 1
            } else
                totalSegundos = totalSegundos + 1
        }
    }
}

@Composable
fun TopLevel(model: ViewModelTimer = viewModel()){
    //timerScreen(minutos = model.totalMinutos, segundos = model.totalSegundos){model.addingTime()}
}

@Composable
fun timerScreen(minutos: Int, segundos: Int, addCount: () -> Unit = {}){
    Text(
        text = "0 $minutos : $segundos",
        fontSize = 40.sp,
        color = MaterialTheme.colors.primary,
        textAlign = TextAlign.Center
    )
    addCount
}



/*
@Preview
@Composable
fun timer() {
    var seconds by remember {
        mutableStateOf(0)
    }
    var minutes by remember {
        mutableStateOf(0)
    }
    Text(
        text = "0 $minutes : $testingSeconds",
        fontSize = 40.sp,
        color = MaterialTheme.colors.primary,
        textAlign = TextAlign.Center
    )

    LaunchedEffect(key1 = testingSeconds, key2 = minutes){
        delay(1000)
        if(testingSeconds % 59 == 0 && testingSeconds != 0){
            testingSeconds = 0
            minutes = minutes + 1
        }
        else
            testingSeconds = testingSeconds + 1
    }
    println(testingSeconds)
}*/

//endregion


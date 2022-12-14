package com.example.datingorrelated

import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

// GLOBAL VARIABLES
data class Response(var _datingBtnColor: Int, var _relatedBtnColor: Int, var _bothBtnColor: Int, var _isAnswerCorrect: Boolean)
var mQuestionsList = Constants.getQuestions()
var shuffledQuestions = mQuestionsList.shuffled()
var testingSeconds = 0

@Composable
fun GameScreen(navController: NavController, mapName: String) {

    //region --- Variables Region ---

    // To change the color of the buttons based on the answer
    val primaryColor = MaterialTheme.colors.primary
    var datingBtnColor by rememberSaveable { mutableStateOf(primaryColor.toArgb()) }
    var relatedBtnColor by rememberSaveable { mutableStateOf(primaryColor.toArgb()) }
    var bothBtnColor by rememberSaveable { mutableStateOf(primaryColor.toArgb()) }

    // To manage the questions
    var mCurrentPosition by rememberSaveable { mutableStateOf(0) }
    val question = shuffledQuestions[mCurrentPosition]
    //val maximum = mQuestionsList.size
    val maximum = 10
    var canClick by rememberSaveable { mutableStateOf(true) }
    var correct by rememberSaveable { mutableStateOf(0) }

    // To manage the answer timer
    var secondsToDisappear by rememberSaveable { mutableStateOf(answerTime) }

    // To manage the sound effects
    val context = LocalContext.current
    val correct_sound = R.raw.correct_sound
    val wrong_sound = R.raw.wrong_sound

    //endregion

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CorrectAnswersText(number = correct)
        QuestionText(question.question)
        Row( // Images
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            QuestionImage(question.firstImage)
            Spacer(modifier = Modifier.width(10.dp))
            QuestionImage(question.secondImage)
        }
        Spacer(modifier = Modifier.size(10.dp))
        // Dating button
        Button(
            onClick = {
                if (canClick){
                    canClick = false // it has just clicked, so it can't click again

                    secondsToDisappear = answerTime // the user has answered, rewind timer

                    val(_datingBtnColor, _relatedBtnColor, _bothBtnColor, isAnswerCorrect) = handleResponse("Dating", question)

                    // reassign colors based on answer
                    datingBtnColor = _datingBtnColor
                    relatedBtnColor = _relatedBtnColor
                    bothBtnColor = _bothBtnColor

                    if (isAnswerCorrect){
                        correct++
                        playSoundEffect(context = context, soundEffect = correct_sound)
                    } else{
                        playSoundEffect(context = context, soundEffect = wrong_sound)
                    }

                    Handler(Looper.getMainLooper()).postDelayed({
                        canClick = true // when the time passes, we go to the next question, so user is able to click again
                        // change color back to default
                        datingBtnColor = primaryColor.toArgb()
                        relatedBtnColor = primaryColor.toArgb()
                        bothBtnColor = primaryColor.toArgb()

                        if(mCurrentPosition == maximum-1){
                            navController.navigate(Screen.EndGameScreen.createRoute(correct, mapName, testingSeconds))
// go to end screen
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
        Spacer(modifier = Modifier.size(10.dp))
        // Related button
        Button(
            onClick = {
                if (canClick){
                    canClick = false // it has just clicked, so it can't click again

                    secondsToDisappear = answerTime // the user has answered, rewind timer

                    val(_datingBtnColor, _relatedBtnColor, _bothBtnColor, isAnswerCorrect) = handleResponse("Related", question)

                    // reassign colors based on answer
                    datingBtnColor = _datingBtnColor
                    relatedBtnColor = _relatedBtnColor
                    bothBtnColor = _bothBtnColor

                    if (isAnswerCorrect){
                        correct++
                        playSoundEffect(context = context, soundEffect = correct_sound)
                    } else{
                        playSoundEffect(context = context, soundEffect = wrong_sound)
                    }

                    Handler(Looper.getMainLooper()).postDelayed({
                        canClick = true // when the time passes, we go to the next question, so user is able to click again

                        // change color back to default
                        datingBtnColor = primaryColor.toArgb()
                        relatedBtnColor = primaryColor.toArgb()
                        bothBtnColor = primaryColor.toArgb()

                        if(mCurrentPosition == maximum-1){
                            navController.navigate(Screen.EndGameScreen.createRoute(correct, mapName, testingSeconds)) // go to end screen

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
        Spacer(modifier = Modifier.size(10.dp))
        // Both button
        Button(
            onClick = {
                if (canClick){
                    canClick = false // it has just clicked, so it can't click again

                    secondsToDisappear = answerTime // the user has answered, rewind timer

                    val(_datingBtnColor, _relatedBtnColor, _bothBtnColor, isAnswerCorrect) = handleResponse("Both", question)

                    // reassign colors based on answer
                    datingBtnColor = _datingBtnColor
                    relatedBtnColor = _relatedBtnColor
                    bothBtnColor = _bothBtnColor

                    if (isAnswerCorrect){
                        correct++
                        playSoundEffect(context = context, soundEffect = correct_sound)
                    } else{
                        playSoundEffect(context = context, soundEffect = wrong_sound)
                    }

                    Handler(Looper.getMainLooper()).postDelayed({
                        canClick = true // when the time passes, we go to the next question, so user is able to click again

                        // change color back to default
                        datingBtnColor = primaryColor.toArgb()
                        relatedBtnColor = primaryColor.toArgb()
                        bothBtnColor = primaryColor.toArgb()

                        if(mCurrentPosition == maximum-1){
                            navController.navigate(Screen.EndGameScreen.createRoute(correct, mapName, testingSeconds)) // go to end screen

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

        TimerText(secondsToDisappear) // timer of questions
        Spacer(modifier = Modifier.size(10.dp))

        Row{
            Text("Your time:",
                fontSize = 32.sp,
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Center,
            )
        }
        Row{
            Timer() // game timer
        }




    }

    // To manage the answer time
    LaunchedEffect(Unit) {
        while(true){
            while (secondsToDisappear >= 0) {
                delay(1.seconds)
                secondsToDisappear -= 1
            }
            // time has passed and the user hasn't responded, go to next question
            if(mCurrentPosition == maximum-1){
                navController.navigate(Screen.EndGameScreen.createRoute(correct, mapName, testingSeconds)) // go to end screen
            }else{
                mCurrentPosition++ // go to next question
                secondsToDisappear = answerTime // restore time
            }
        }
    }
}

//region Questions related methods
fun handleResponse(button: String, question: Question): Response{
    if(showAnswer){
        val(_datingBtnColor, _relatedBtnColor, _bothBtnColor, _isAnswerCorrect) = handleResponseShowAnswer(button, question)
        return Response(_datingBtnColor, _relatedBtnColor, _bothBtnColor, _isAnswerCorrect)
    } else{
        val(_datingBtnColor, _relatedBtnColor, _bothBtnColor, _isAnswerCorrect) = handleResponseDoNotShowAnswer(button, question)
        return Response(_datingBtnColor, _relatedBtnColor, _bothBtnColor, _isAnswerCorrect)
    }
}

fun handleResponseShowAnswer(button: String, question: Question): Response{
    var _datingBtnColor = 0
    var _bothBtnColor = 0
    var _relatedBtnColor = 0
    var _isAnswerCorrect = false

    when(button){
        "Dating" -> when(question.answer){
            "Dating" -> {
                _datingBtnColor = Color.Green.toArgb()
                _bothBtnColor = Color.Gray.toArgb()
                _relatedBtnColor = Color.Gray.toArgb()
                _isAnswerCorrect = true
            }
            "Both" -> {
                _bothBtnColor = Color.Yellow.toArgb()
                _relatedBtnColor = Color.Gray.toArgb()
                _datingBtnColor = Color.Red.toArgb()
            }
            "Related" -> {
                _relatedBtnColor = Color.Yellow.toArgb()
                _bothBtnColor = Color.Gray.toArgb()
                _datingBtnColor = Color.Red.toArgb()

            }
        }
        "Both" -> when(question.answer){
            "Dating" -> {
                _bothBtnColor = Color.Red.toArgb()
                _datingBtnColor = Color.Yellow.toArgb()
                _relatedBtnColor = Color.Gray.toArgb()
            }
            "Both" -> {
                _bothBtnColor = Color.Green.toArgb()
                _datingBtnColor = Color.Gray.toArgb()
                _relatedBtnColor = Color.Gray.toArgb()
                _isAnswerCorrect = true
            }
            "Related" -> {
                _bothBtnColor = Color.Red.toArgb()
                _relatedBtnColor = Color.Yellow.toArgb()
                _datingBtnColor = Color.Gray.toArgb()
            }
        }
        "Related" -> when(question.answer){
            "Dating" -> {
                _datingBtnColor = Color.Yellow.toArgb()
                _bothBtnColor = Color.Gray.toArgb()
                _relatedBtnColor = Color.Red.toArgb()
            }
            "Both" -> {
                _bothBtnColor = Color.Yellow.toArgb()
                _datingBtnColor = Color.Gray.toArgb()
                _relatedBtnColor = Color.Red.toArgb()
            }
            "Related" -> {
                _relatedBtnColor = Color.Green.toArgb()
                _bothBtnColor = Color.Gray.toArgb()
                _datingBtnColor = Color.Gray.toArgb()
                _isAnswerCorrect = true
            }
        }
    }

    return Response(_datingBtnColor, _relatedBtnColor, _bothBtnColor, _isAnswerCorrect)
}

fun handleResponseDoNotShowAnswer(button: String, question: Question): Response{
    var _datingBtnColor = 0
    var _bothBtnColor = 0
    var _relatedBtnColor = 0
    var _isAnswerCorrect = false

    when(button){
        "Dating" -> when(question.answer){
            "Dating" -> {
                _datingBtnColor = Color.Green.toArgb()
                _bothBtnColor = Color.Gray.toArgb()
                _relatedBtnColor = Color.Gray.toArgb()
                _isAnswerCorrect = true
            }
            "Both" -> {
                _bothBtnColor = Color.Gray.toArgb()
                _relatedBtnColor = Color.Gray.toArgb()
                _datingBtnColor = Color.Red.toArgb()
            }
            "Related" -> {
                _relatedBtnColor = Color.Gray.toArgb()
                _bothBtnColor = Color.Gray.toArgb()
                _datingBtnColor = Color.Red.toArgb()

            }
        }
        "Both" -> when(question.answer){
            "Dating" -> {
                _bothBtnColor = Color.Red.toArgb()
                _datingBtnColor = Color.Gray.toArgb()
                _relatedBtnColor = Color.Gray.toArgb()
            }
            "Both" -> {
                _bothBtnColor = Color.Green.toArgb()
                _datingBtnColor = Color.Gray.toArgb()
                _relatedBtnColor = Color.Gray.toArgb()
                _isAnswerCorrect = true
            }
            "Related" -> {
                _bothBtnColor = Color.Red.toArgb()
                _relatedBtnColor = Color.Gray.toArgb()
                _datingBtnColor = Color.Gray.toArgb()
            }
        }
        "Related" -> when(question.answer){
            "Dating" -> {
                _datingBtnColor = Color.Gray.toArgb()
                _bothBtnColor = Color.Gray.toArgb()
                _relatedBtnColor = Color.Red.toArgb()
            }
            "Both" -> {
                _bothBtnColor = Color.Gray.toArgb()
                _datingBtnColor = Color.Gray.toArgb()
                _relatedBtnColor = Color.Red.toArgb()
            }
            "Related" -> {
                _relatedBtnColor = Color.Green.toArgb()
                _bothBtnColor = Color.Gray.toArgb()
                _datingBtnColor = Color.Gray.toArgb()
                _isAnswerCorrect = true
            }
        }
    }

    return Response(_datingBtnColor, _relatedBtnColor, _bothBtnColor, _isAnswerCorrect)
}

fun playSoundEffect(context: Context, soundEffect: Int){
    val mp = MediaPlayer.create(context, soundEffect)
    mp.setVolume(soundEffectsVolume, soundEffectsVolume)
    mp.start()
    mp.setOnCompletionListener {
        MediaPlayer.OnCompletionListener {
            mp.reset()
            mp.release()
        }
    }
}

@Composable
fun TimerText(number: Int){
    var color = MaterialTheme.colors.onBackground
    if (number == 0){
        color = Color.Red
    }

    Text(
        text = "Question time left: $number",
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
        color = MaterialTheme.colors.onBackground,
        textAlign = TextAlign.Center
    )
}

@Composable
fun ButtonText(name: String) {
    Text(
        text = name,
        fontSize = 32.sp,
        color = MaterialTheme.colors.onPrimary,
        modifier = Modifier.width(150.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
fun QuestionText(question: String) {
    Text(
        text = question,
        fontSize = 32.sp,
        color = MaterialTheme.colors.onBackground,
        textAlign = TextAlign.Center
    )
}

@Composable
fun QuestionImage(image: Int) {
    Image(
        painter = painterResource(image),
        contentDescription = "Contact profile picture",
        modifier = Modifier
            .size(150.dp)
            .clip(CircleShape)
            .border(10.dp, MaterialTheme.colors.secondary, CircleShape)
    )
}

//endregion

//region Timer
@Composable
fun Timer() {
    var seconds by remember {
        mutableStateOf(0)
    }
    var minutes by remember {
        mutableStateOf(0)
    }

    if(seconds < 10){
        Text(
            text = "0$minutes : 0$seconds",
            fontSize = 40.sp,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center
        )
    } else{
        Text(
            text = "0 $minutes : $seconds",
            fontSize = 40.sp,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center
        )
    }

    LaunchedEffect(key1 = seconds, key2 = minutes){
        delay(1000)
        if(seconds % 59 == 0 && seconds != 0){
            seconds = 0
            minutes++
        }
        else
            seconds++
    }
    testingSeconds = seconds
}

//endregion
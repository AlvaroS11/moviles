package com.example.datingorrelated

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColor
import com.example.datingorrelated.Constants
import com.example.datingorrelated.Question
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}


@Preview
@Composable
fun MainScreen() {
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
                //GameScreen()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)
        ) {
            Text(
                text = "Start button",
                fontSize = 32.sp,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}
//////////////////////////


data class Response(var _datingBtnColor: Int, var _relatedBtnColor: Int, var _bothBtnColor: Int, var _correct: Int)


@Preview
@Composable
fun GameScreen() {
    var mQuestionsList = Constants.getQuestions()
    var primaryColor = MaterialTheme.colors.primary

    var mCurrentPosition by rememberSaveable { mutableStateOf(0) }

    var correct by rememberSaveable { mutableStateOf(0) }

    var datingBtnColor by rememberSaveable { mutableStateOf(primaryColor.toArgb())}
    var relatedBtnColor by rememberSaveable() { mutableStateOf(primaryColor.toArgb())}
    var bothBtnColor by rememberSaveable() { mutableStateOf(primaryColor.toArgb())}

    val question = mQuestionsList!![mCurrentPosition]

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CorrectAnswersText(number = correct)
        QuestionText(question.question)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            QuestionImage(question.firstImage)
            QuestionImage(question.secondImage)
        }
        // Dating button
        Button(
            onClick = {

                val(_datingBtnColor, _relatedBtnColor, _bothBtnColor, _correct) = handleResponse("Dating", question, correct)

                datingBtnColor = _datingBtnColor
                relatedBtnColor = _relatedBtnColor
                bothBtnColor = _bothBtnColor
                correct = _correct

                Handler(Looper.getMainLooper()).postDelayed({
                    datingBtnColor = primaryColor.toArgb()
                    relatedBtnColor = primaryColor.toArgb()
                    bothBtnColor = primaryColor.toArgb()
                    mCurrentPosition++
                }, 500)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(datingBtnColor))
        ) {
            ButtonText("Dating")
        }
        // Related button
        Button(
            onClick = {

                val(_datingBtnColor, _relatedBtnColor, _bothBtnColor, _correct) = handleResponse("Related", question, correct)

                datingBtnColor = _datingBtnColor
                relatedBtnColor = _relatedBtnColor
                bothBtnColor = _bothBtnColor
                correct = _correct

                Handler(Looper.getMainLooper()).postDelayed({
                    datingBtnColor = primaryColor.toArgb()
                    relatedBtnColor = primaryColor.toArgb()
                    bothBtnColor = primaryColor.toArgb()
                    mCurrentPosition++
                }, 500)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(relatedBtnColor))
        ) {
            ButtonText("Related")
        }
        // Both button
        Button(
            onClick = {

                val(_datingBtnColor, _relatedBtnColor, _bothBtnColor, _correct) = handleResponse("Both", question, correct)

                datingBtnColor = _datingBtnColor
                relatedBtnColor = _relatedBtnColor
                bothBtnColor = _bothBtnColor
                correct = _correct

                Handler(Looper.getMainLooper()).postDelayed({
                    datingBtnColor = primaryColor.toArgb()
                    relatedBtnColor = primaryColor.toArgb()
                    bothBtnColor = primaryColor.toArgb()
                    mCurrentPosition++
                }, 500)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(bothBtnColor))
        ) {
            ButtonText("Both")
        }

    }
}

fun handleResponse(button: String, question: Question, correct: Int): Response{
    var _datingBtnColor = 0
    var _bothBtnColor = 0
    var _relatedBtnColor = 0
    var _correct = correct

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
                _relatedBtnColor = Color.Red.toArgb()
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
fun CorrectAnswersText(number: Int){
    Text(
        text = "Correct answers: $number",
        fontSize = 24.sp,
        color = MaterialTheme.colors.onPrimary,
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
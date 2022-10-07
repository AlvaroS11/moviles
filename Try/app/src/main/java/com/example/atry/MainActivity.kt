package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atry.Constants
import com.example.atry.Question

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

@Preview
@Composable
fun GameScreen() {
    var mCurrentPosition by rememberSaveable { mutableStateOf(1) }
    var correct by rememberSaveable { mutableStateOf(0) }

    var mQuestionsList = Constants.getQuestions()
    val question = mQuestionsList!![mCurrentPosition - 1]

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
        Button(
            onClick = {
                if (question!!.answer == "Dating") {
                    correct++
                }
                mCurrentPosition++
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)
        ) {
            ButtonText("Dating")
        }
        Button(
            onClick = {
                if (question!!.answer == "Related") {
                    correct++
                }
                mCurrentPosition++
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)
        ) {
            ButtonText("Related")
        }
        Button(
            onClick = {
                if (question!!.answer == "Both") {
                    correct++
                }
                mCurrentPosition++
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)
        ) {
            ButtonText("Both")
        }

    }
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
        color = MaterialTheme.colors.primary,
        textAlign = TextAlign.Center
    )
}

@Composable
fun QuestionText(question: String) {
    Text(
        text = "$question",
        fontSize = 32.sp,
        color = MaterialTheme.colors.primary,
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
package com.joincoded.truefalsecomposegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joincoded.truefalsecomposegame.ui.theme.TrueFalseComposeGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrueFalseComposeGameTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameScreen()
                }
            }
        }
    }
}

@Composable
fun GameScreen(modifier: Modifier = Modifier) {
    val questions = listOf("Is this topic interesting ?", "are you morning person ?", "Coded is a comfy environment ?")
    val answers = listOf(true, false, true)
    var currentQuestionIndex by remember {
        mutableIntStateOf(0)
    }
    var showCorrectResult by remember {
        mutableStateOf(false)
    }
    var showWrongResult by remember {
        mutableStateOf(false)
    }
    var showNextQuestionButton by remember {
        mutableStateOf(false)
    }
    var showAnswersOptionsRow by remember {
        mutableStateOf(true)
    }
    
    var userScore by remember {
        mutableStateOf(0)
    }
    var answerEvaluated by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier.padding(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = questions[currentQuestionIndex])

        if (showWrongResult)
            AnswerResult(text = stringResource(R.string.wrong_answer), color = Color.Red)
        showAnswersOptionsRow = false

        if (showCorrectResult)
            AnswerResult(text = stringResource(R.string.correct_answer), color = Color.Green)
        userScore++
        showAnswersOptionsRow = true

        if (showNextQuestionButton)
            Button(modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (currentQuestionIndex == questions.size - 1)
                        currentQuestionIndex = 0
                    else
                        currentQuestionIndex++
                    showNextQuestionButton = false
                    showAnswersOptionsRow = true
                    showCorrectResult = false
                    showWrongResult = false
                }) {
                Text(text = stringResource(R.string.next_question))
            }

        if (showAnswersOptionsRow)
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(modifier = Modifier.weight(1f),
                    onClick = {
                        val isAnswerCorrect = true == answers[currentQuestionIndex]
                        if (isAnswerCorrect) {
                            showCorrectResult = true
                            showNextQuestionButton = true
                            showAnswersOptionsRow = false
                            showWrongResult = false
                        } else {
                            showWrongResult = true
                        }
                    }) {
                    Text(text = stringResource(R.string.true_text))
                }

                Spacer(modifier = Modifier.width(10.dp))

                Button(modifier = Modifier.weight(1f),
                    onClick = {
                        val isAnswerCorrect = false == answers[currentQuestionIndex]
                        if (isAnswerCorrect) {
                            showCorrectResult = true
                            showNextQuestionButton = true
                            showAnswersOptionsRow = false
                            showWrongResult = false
                        } else {
                            showWrongResult = true


                        }
                    }) {
                    Text(text = stringResource(R.string.false_text))
                }


            }
        Text(text = "Score: $userScore")
    }

}


@Composable
fun AnswerResult(text: String, color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(200.dp)
            .clip(CircleShape)
            .background(color)
    ) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TrueFalseComposeGameTheme {
    }
}
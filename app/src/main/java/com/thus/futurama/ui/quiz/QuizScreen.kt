package com.thus.futurama.ui.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thus.futurama.R
import com.thus.futurama.data.model.QuizResponse
import com.thus.futurama.ui.commonscreens.EmptyScreen
import com.thus.futurama.ui.commonscreens.ErrorScreen
import com.thus.futurama.ui.commonscreens.LoadingScreen

@Composable
fun QuizScreen(navController: NavController, quizViewModel: QuizViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = stringResource(id = R.string.text_quiz),
                    style = MaterialTheme.typography.h6
                )
            }, navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "back icon")
                }
            })
        }
    )
    { _ ->
        when (val state = quizViewModel.quizState.value) {
            is QuizState.Loading -> {
                LoadingScreen()
            }

            is QuizState.Empty -> {
                EmptyScreen()
            }

            is QuizState.Normal -> {
                QuizScreenNormal(
                    questions = state.quiz,
                    onRestartQuiz = { quizViewModel.retry() }
                )
            }

            is QuizState.Error -> {
                ErrorScreen {
                    quizViewModel.retry()
                }
            }
        }
    }
}

@Composable
fun QuizScreenNormal(
    questions: List<QuizResponse>,
    onRestartQuiz: () -> Unit
) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }

    if (currentQuestionIndex < questions.size) {
        val question = questions[currentQuestionIndex]

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = question.question ?: "")
            Spacer(modifier = Modifier.height(16.dp))
            question.possibleAnswers.forEach { answer ->
                AnswerButton(
                    text = answer,
                    isSelected = false,
                    onClick = {
                        if (answer == question.correctAnswer) {
                            score++
                        }
                        currentQuestionIndex++
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(R.string.quiz_score, score, questions.size))
        }

    } else {
        ResultScreen(score = score, onRestartQuiz = onRestartQuiz)
    }
}

@Composable
fun AnswerButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        enabled = isSelected.not()
    ) {
        Text(text = text)
    }
}

@Composable
fun ResultScreen(
    score: Int,
    onRestartQuiz: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.quiz_result, score),
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRestartQuiz) {
            Text(text = stringResource(R.string.quiz_restart))
        }
    }

}

@Preview
@Composable
fun Preview() {
    val questions = listOf(
        QuizResponse(
            id = 1,
            question = "What is Fry's first name?",
            possibleAnswers = listOf("Fred", "Philip", "Will", "John"),
            correctAnswer = "Philip"
        ),
        QuizResponse(
            id = 2,
            question = "In 'Benders Big Score' what alien species scam the earth?",
            possibleAnswers = listOf(
                "Nibbloniens",
                "Omicrons",
                "Robots",
                "Nudest aliens",
                "Tentacles"
            ),
            correctAnswer = "Nudest aliens"
        ),
        QuizResponse(
            id = 3,
            question = "What is Bender's middle and last name?",
            possibleAnswers = listOf(
                "E Smithie",
                "Flam Flexo",
                "Lobster Squid",
                "Bending Rodriguez",
                "Steven Martin"
            ),
            correctAnswer = "Bending Rodriguez"
        )
    )

    QuizScreenNormal(questions = questions, onRestartQuiz = {})
}
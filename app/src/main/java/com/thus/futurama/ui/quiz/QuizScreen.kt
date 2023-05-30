package com.thus.futurama.ui.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thus.futurama.R
import com.thus.futurama.domain.model.QuestionInfo
import com.thus.futurama.ui.commonscreens.EmptyScreen
import com.thus.futurama.ui.commonscreens.ErrorScreen
import com.thus.futurama.ui.commonscreens.LoadingScreen
import com.thus.futurama.ui.theme.spacing

@Composable
fun QuizScreen(
    navController: NavController,
    quizViewModel: QuizViewModel,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.screen_name_quiz),
                        style = MaterialTheme.typography.h6
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "back icon")
                    }
                }
            )
        }
    ) { paddingValues ->
        when (val state = quizViewModel.quizState.value) {
            is QuizState.Loading -> {
                LoadingScreen()
            }

            is QuizState.Empty -> {
                EmptyScreen()
            }

            is QuizState.Error -> {
                ErrorScreen {
                    quizViewModel.refresh()
                }
            }

            is QuizState.Normal -> {
                QuizScreenNormal(
                    paddingValues = paddingValues,
                    gameInfo = state.gameInfo,
                    onRestartQuiz = {
                        quizViewModel.refresh()
                    }
                )
            }
        }
    }
}

@Composable
fun QuizScreenNormal(
    paddingValues: PaddingValues,
    gameInfo: GameInfo,
    onRestartQuiz: () -> Unit
) {
    val questions = gameInfo.questions
    val currentQuestionIndex = gameInfo.currentQuestionIndex
    val score = gameInfo.score
    val state = rememberScrollState()
    val progress = (currentQuestionIndex.value + 1) / questions.size.toFloat()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        if (currentQuestionIndex.value < questions.size) {
            Row(
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
            ) {
                Text(
                    text = stringResource(R.string.quiz_score, score.value),
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(
                        top = MaterialTheme.spacing.medium,
                        bottom = MaterialTheme.spacing.small
                    )
                )

                Spacer(modifier = Modifier.weight(1f, true))

                Text(
                    text = stringResource(
                        R.string.text_question_progress,
                        currentQuestionIndex.value + 1,
                        questions.size
                    ),
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(
                        top = MaterialTheme.spacing.medium,
                        bottom = MaterialTheme.spacing.small
                    )
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.medium)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress)
                        .height(4.dp)
                        .background(MaterialTheme.colors.primary)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .background(MaterialTheme.colors.onSurface.copy(alpha = 0.2f))
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            val question = questions[currentQuestionIndex.value]

            Column(
                modifier = Modifier
                    .verticalScroll(state)
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .weight(1f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = question.question,
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = MaterialTheme.spacing.medium)
                )
                Column(Modifier.padding(bottom = MaterialTheme.spacing.medium)) {
                    question.possibleAnswers.forEach { answer ->
                        AnswerButton(
                            text = answer,
                            isSelected = false,
                            onClick = {
                                if (answer == question.correctAnswer) {
                                    score.value++
                                }
                                currentQuestionIndex.value++
                            }
                        )
                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
                    }
                }
            }
        } else {
            ResultScreen(score = score.value, onRestartQuiz = onRestartQuiz)
        }
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
        modifier = Modifier
            .fillMaxWidth(),
        enabled = !isSelected
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(MaterialTheme.spacing.small)
        )
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
            .padding(MaterialTheme.spacing.medium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.quiz_result, score),
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        Button(onClick = onRestartQuiz) {
            Text(text = stringResource(R.string.quiz_restart))
        }
    }
}

@Preview
@Composable
fun PreviewQuizScreen() {
    val questions = listOf(
        QuestionInfo(
            id = 1,
            question = "What is Fry's first name?",
            possibleAnswers = listOf("Fred", "Philip", "Will", "John"),
            correctAnswer = "Philip"
        )
    )

    QuizScreenNormal(
        paddingValues = PaddingValues(),
        gameInfo = GameInfo(questions),
        onRestartQuiz = {})
}
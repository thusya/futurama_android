package com.thus.futurama.ui.quiz

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.thus.futurama.domain.model.QuestionInfo

data class GameInfo(
    val questions: List<QuestionInfo> = emptyList(),
    val currentQuestionIndex: MutableState<Int> = mutableStateOf(0),
    val score: MutableState<Int> = mutableStateOf(0)
)
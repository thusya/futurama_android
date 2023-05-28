package com.thus.futurama.ui.quiz

import com.thus.futurama.data.model.QuizResponse

sealed class QuizState {
    object Loading: QuizState()
    object Error: QuizState()
    object Empty: QuizState()
    data class Normal(val quiz: List<QuizResponse>): QuizState()
}
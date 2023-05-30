package com.thus.futurama.ui.quiz

sealed class QuizState {
    object Loading : QuizState()
    object Empty : QuizState()
    data class Error(val e: Exception) : QuizState()
    data class Normal(val gameInfo: GameInfo) : QuizState()
}
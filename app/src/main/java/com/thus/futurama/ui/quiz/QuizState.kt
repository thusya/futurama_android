package com.thus.futurama.ui.quiz

import com.thus.futurama.domain.model.QuestionInfo

sealed class QuizState {
    object Loading: QuizState()
    object Error: QuizState()
    object Empty: QuizState()
    data class Normal(val quiz: List<QuestionInfo>): QuizState()
}
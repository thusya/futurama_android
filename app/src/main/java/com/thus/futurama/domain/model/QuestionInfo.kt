package com.thus.futurama.domain.model

data class QuestionInfo(
    val id: Int = 0,
    val question: String = "",
    val possibleAnswers: List<String> = emptyList(),
    val correctAnswer: String = ""
)

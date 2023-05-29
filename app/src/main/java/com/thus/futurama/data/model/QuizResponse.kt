package com.thus.futurama.data.model

import com.squareup.moshi.Json
import com.thus.futurama.domain.model.QuestionInfo

data class QuizResponse(
    @field:Json(name = "id")
    val id: Int? = null,

    @field:Json(name = "question")
    val question: String? = null,

    @field:Json(name = "possibleAnswers")
    val possibleAnswers: List<String>? = null,

    @field:Json(name = "correctAnswer")
    val correctAnswer: String? = null
)

fun QuizResponse.toQuestionInfo() = QuestionInfo(
    id = id ?: 0,
    question = question.orEmpty(),
    possibleAnswers = possibleAnswers.orEmpty(),
    correctAnswer = correctAnswer.orEmpty()
)


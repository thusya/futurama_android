package com.thus.futurama.data.model

import com.squareup.moshi.Json

data class QuizResponse(
    @field:Json(name = "id") val id: Int = 0,
    @field:Json(name = "question") val question: String? = "",
    @field:Json(name = "possibleAnswers") val possibleAnswers: List<String> = emptyList(),
    @field:Json(name = "correctAnswer") val correctAnswer: String? = ""
)


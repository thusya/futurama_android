package com.thus.futurama.domain.repository

import com.thus.futurama.data.model.CharacterResponse
import com.thus.futurama.data.model.ShowResponse
import com.thus.futurama.data.model.QuizResponse
import com.thus.futurama.domain.model.CharacterInfo
import com.thus.futurama.domain.model.QuestionInfo
import com.thus.futurama.domain.model.ShowInfo

interface FuturamaRepository {
    suspend fun getShowInfo(): List<ShowInfo>

    suspend fun getCharacters(): List<CharacterInfo>

    suspend fun getQuiz(): List<QuestionInfo>
}
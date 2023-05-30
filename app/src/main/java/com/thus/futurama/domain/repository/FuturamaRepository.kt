package com.thus.futurama.domain.repository

import com.thus.futurama.domain.model.CharacterInfo
import com.thus.futurama.domain.model.QuestionInfo
import com.thus.futurama.domain.model.ShowInfo

interface FuturamaRepository {
    suspend fun getShowInfo(): List<ShowInfo>

    suspend fun getCharacters(): List<CharacterInfo>

    suspend fun getRandomQuestions(): List<QuestionInfo>
}
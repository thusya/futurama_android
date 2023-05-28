package com.thus.futurama.domain.repository

import com.thus.futurama.data.model.CharacterResponse
import com.thus.futurama.data.model.HomeScreenResponse
import com.thus.futurama.data.model.QuizResponse

interface HomeRepository {
    suspend fun getHomeScreenData(): List<HomeScreenResponse>

    suspend fun getCharacters(): List<CharacterResponse>

    suspend fun getQuiz(): List<QuizResponse>
}
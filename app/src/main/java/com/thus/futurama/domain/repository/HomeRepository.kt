package com.thus.futurama.domain.repository

import com.thus.futurama.data.model.CharacterResponse
import com.thus.futurama.data.model.HomeScreenResponse

interface HomeRepository {
    suspend fun getHomeScreenData(): List<HomeScreenResponse>

    suspend fun getCharacters(): List<CharacterResponse>
}
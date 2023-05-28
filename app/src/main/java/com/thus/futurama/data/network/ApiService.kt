package com.thus.futurama.data.network

import com.thus.futurama.data.model.CharacterResponse
import com.thus.futurama.data.model.HomeScreenResponse
import retrofit2.http.GET

interface ApiService {

    @GET("futurama/info")
    suspend fun getHomeScreenData(): List<HomeScreenResponse>

    @GET("futurama/characters")
    suspend fun getCharacters(): List<CharacterResponse>
}
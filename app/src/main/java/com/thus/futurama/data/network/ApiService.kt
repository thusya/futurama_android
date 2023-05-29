package com.thus.futurama.data.network

import com.thus.futurama.data.model.CharacterResponse
import com.thus.futurama.data.model.ShowResponse
import com.thus.futurama.data.model.QuizResponse
import retrofit2.http.GET

interface ApiService {

    @GET("futurama/info")
    suspend fun getShowInfo(): List<ShowResponse>

    @GET("futurama/characters")
    suspend fun getCharacters(): List<CharacterResponse>

    @GET("futurama/questions")
    suspend fun getQuiz(): List<QuizResponse>
}
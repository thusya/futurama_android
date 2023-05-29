package com.thus.futurama.data.repository

import com.thus.futurama.data.model.toCharacterInfo
import com.thus.futurama.data.model.toQuestionInfo
import com.thus.futurama.data.model.toShowInfo
import com.thus.futurama.data.network.ApiService
import com.thus.futurama.domain.model.CharacterInfo
import com.thus.futurama.domain.model.QuestionInfo
import com.thus.futurama.domain.model.ShowInfo
import com.thus.futurama.domain.repository.FuturamaRepository

class FuturamaRepositoryImpl(private val apiService: ApiService) : FuturamaRepository {

    override suspend fun getShowInfo(): List<ShowInfo> {
        return apiService.getShowInfo()
            .map { it.toShowInfo() }
    }

    override suspend fun getCharacters(): List<CharacterInfo> {
        return apiService.getCharacters()
            .map { it.toCharacterInfo() }
    }

    override suspend fun getQuiz(): List<QuestionInfo> {
        return apiService.getQuiz()
            .map { it.toQuestionInfo() }
    }


}
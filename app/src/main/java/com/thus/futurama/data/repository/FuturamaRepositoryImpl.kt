package com.thus.futurama.data.repository

import com.thus.futurama.data.model.toCharacterInfo
import com.thus.futurama.data.model.toQuestionInfo
import com.thus.futurama.data.model.toShowInfo
import com.thus.futurama.data.network.ApiService
import com.thus.futurama.domain.model.CharacterInfo
import com.thus.futurama.domain.model.QuestionInfo
import com.thus.futurama.domain.model.ShowInfo
import com.thus.futurama.domain.repository.FuturamaRepository
import java.util.ArrayList
import java.util.Collections

class FuturamaRepositoryImpl(private val apiService: ApiService) : FuturamaRepository {

    private val questions = Collections.synchronizedList(ArrayList<QuestionInfo>())
    private val maxItemCount = 20

    override suspend fun getShowInfo(): List<ShowInfo> {
        return apiService.getShowInfo()
            .map { it.toShowInfo() }
    }

    override suspend fun getCharacters(): List<CharacterInfo> {
        return apiService.getCharacters()
            .map { it.toCharacterInfo() }
    }

    override suspend fun getRandomQuestions(): List<QuestionInfo> {
        if (questions.isEmpty()) {
            val response = apiService.getQuiz()
            questions.clear()
            questions.addAll(response.map {
                it.toQuestionInfo()
            })
        }
        return pickRandomQuestions(questions, maxItemCount)
    }

    private fun pickRandomQuestions(
        questionInfoList: List<QuestionInfo>,
        count: Int
    ): List<QuestionInfo> {
        val randomizedList = questionInfoList.shuffled()
        return randomizedList.subList(0, count.coerceAtMost(randomizedList.size))
    }

}
package com.thus.futurama.ui.quiz

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thus.futurama.domain.repository.FuturamaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuizViewModel(private val futuramaRepository: FuturamaRepository) : ViewModel() {

    val quizState = mutableStateOf<QuizState>(QuizState.Loading)

    init {
        refresh()
    }

    fun refresh() {
        quizState.value = QuizState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = futuramaRepository.getQuiz()

                if (response.isEmpty()) {
                    quizState.value = QuizState.Empty
                } else {
                    quizState.value = QuizState.Normal(response)
                }
            } catch (e: Exception) {
                quizState.value = QuizState.Error(e)
            }
        }
    }

}
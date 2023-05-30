package com.thus.futurama.ui.quiz

import com.thus.futurama.domain.model.QuestionInfo
import com.thus.futurama.domain.repository.FuturamaRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class QuizViewModelTest {
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: QuizViewModel
    private lateinit var futuramaRepository: FuturamaRepository

    @Before
    fun setup() {
        futuramaRepository = mockk()
        viewModel = QuizViewModel(futuramaRepository, testDispatcher)
    }

    @Test
    fun `refresh should set quizState to Normal when question list is not empty`() {
        // Given
        val questionInfoList = listOf(
            QuestionInfo(
                id = 1,
                question = "What is Fry's first name?",
                possibleAnswers = listOf("Fred", "Philip", "Will", "John"),
                correctAnswer = "Philip"
            )
        )
        coEvery { futuramaRepository.getRandomQuestions() } returns questionInfoList

        // When
        viewModel.refresh()

        // Then
        assertEquals(
            questionInfoList,
            (viewModel.quizState.value as QuizState.Normal).gameInfo.questions
        )
    }

    @Test
    fun `refresh should set quizState to Empty when question list is empty`() {
        // Given
        val emptyQuestionInfoList = emptyList<QuestionInfo>()
        coEvery { futuramaRepository.getRandomQuestions() } returns emptyQuestionInfoList

        // When
        viewModel.refresh()

        // Then
        assertEquals(QuizState.Empty, viewModel.quizState.value)
    }

    @Test
    fun `refresh should set quizState to Error when an exception occurs`() {
        // Given
        val exception = Exception("error message")
        coEvery { futuramaRepository.getRandomQuestions() } throws exception

        // When
        viewModel.refresh()

        // Then
        assertEquals(QuizState.Error(exception), viewModel.quizState.value)
    }
}
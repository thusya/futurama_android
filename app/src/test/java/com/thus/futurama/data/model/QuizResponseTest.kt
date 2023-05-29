package com.thus.futurama.data.model

import junit.framework.TestCase.assertEquals
import org.junit.Test

class QuizResponseTest {

    @Test
    fun `toQuestionInfo convert QuizResponse to QuestionInfo correctly`() {
        // Arrange
        val quizResponse = QuizResponse(
            id = 1,
            question = "What is Fry's first name?",
            possibleAnswers = listOf("Fred", "Philip", "Will", "John"),
            correctAnswer = "Philip"
        )
        // Act
        val questionInfo = quizResponse.toQuestionInfo()

        // Assert
        assertEquals(1, questionInfo.id)
        assertEquals("What is Fry's first name?", questionInfo.question)
        assertEquals(4, questionInfo.possibleAnswers.size)
        assertEquals("Fred", questionInfo.possibleAnswers[0])
        assertEquals("Philip", questionInfo.possibleAnswers[1])
        assertEquals("Will", questionInfo.possibleAnswers[2])
        assertEquals("John", questionInfo.possibleAnswers[3])
        assertEquals("Philip", questionInfo.correctAnswer)

    }
}
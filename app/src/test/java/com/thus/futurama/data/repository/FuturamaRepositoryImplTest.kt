package com.thus.futurama.data.repository

import com.thus.futurama.data.model.CharacterResponse
import com.thus.futurama.data.model.ImageResponse
import com.thus.futurama.data.model.NameResponse
import com.thus.futurama.data.model.QuizResponse
import com.thus.futurama.data.model.ShowResponse
import com.thus.futurama.data.network.ApiService
import com.thus.futurama.domain.model.CharacterImage
import com.thus.futurama.domain.model.CharacterInfo
import com.thus.futurama.domain.model.CharacterName
import com.thus.futurama.domain.model.QuestionInfo
import com.thus.futurama.domain.model.ShowInfo
import com.thus.futurama.domain.repository.FuturamaRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class FuturamaRepositoryImplTest {

    private lateinit var apiService: ApiService
    private lateinit var futuramaRepository: FuturamaRepository

    @Before
    fun setUp() {
        apiService = mockk(relaxed = true)
        futuramaRepository = FuturamaRepositoryImpl(apiService)
    }

    @Test
    fun `getShowInfo() returns list of ShowInfo`() = runBlocking {
        // Arrange
        val showResponse1 = ShowResponse(
            synopsis = "Futurama is a comedic sci-fi animated series.",
            yearsAired = "1999-2013",
            creators = emptyList(),
            id = 1
        )

        val showResponse2 = ShowResponse(
            synopsis = "Another show",
            yearsAired = "2000-2005",
            creators = emptyList(),
            id = 2
        )

        val expectedShowInfoList = listOf(
            ShowInfo(
                synopsis = "Futurama is a comedic sci-fi animated series.",
                yearsAired = "1999-2013",
                creators = emptyList(),
                id = 1
            ),
            ShowInfo(
                synopsis = "Another show",
                yearsAired = "2000-2005",
                creators = emptyList(),
                id = 2
            )
        )

        coEvery { apiService.getShowInfo() } returns listOf(showResponse1, showResponse2)

        //Act
        val showInfoList = futuramaRepository.getShowInfo()

        //Assert
        assertEquals(showInfoList, expectedShowInfoList)

    }

    @Test
    fun `getCharacters() returns list of CharacterInfo`() = runBlocking {
        // Arrange
        val characterResponse1 = CharacterResponse(
            name = NameResponse(first = "Philip", middle = "J.", last = "Fry"),
            images = ImageResponse(headShot = "headshot.jpg", main = "main.jpg"),
            age = "25",
            gender = "Male",
            species = "Human",
            homePlanet = "Earth",
            occupation = "Delivery Boy",
            sayings = listOf("Good news, everyone!", "Shut up and take my money!"),
            id = 1
        )

        val characterResponse2 = CharacterResponse(
            name = NameResponse(first = "Philip", middle = "J.", last = "Fry"),
            images = ImageResponse(headShot = "headshot.jpg", main = "main.jpg"),
            age = "30",
            gender = "Female",
            species = "Robot",
            homePlanet = "Unknown",
            occupation = "Bending Unit",
            sayings = listOf("Bite my shiny metal ass!", "I'm 40% zinc!"),
            id = 2
        )

        val expectedCharacterInfoList = listOf(
            CharacterInfo(
                id = 1,
                name = CharacterName(first = "Philip", middle = "J.", last = "Fry"),
                images = CharacterImage(headShot = "headshot.jpg", main = "main.jpg"),
                age = "25",
                gender = "Male",
                species = "Human",
                homePlanet = "Earth",
                occupation = "Delivery Boy",
                sayings = listOf("Good news, everyone!", "Shut up and take my money!")
            ),
            CharacterInfo(
                id = 2,
                name = CharacterName(first = "Philip", middle = "J.", last = "Fry"),
                images = CharacterImage(headShot = "headshot.jpg", main = "main.jpg"),
                age = "30",
                gender = "Female",
                species = "Robot",
                homePlanet = "Unknown",
                occupation = "Bending Unit",
                sayings = listOf("Bite my shiny metal ass!", "I'm 40% zinc!")
            )
        )
        coEvery { apiService.getCharacters() } returns listOf(
            characterResponse1,
            characterResponse2
        )
        //Act
        val charactersList = futuramaRepository.getCharacters()

        //Assert
        assertEquals(charactersList, expectedCharacterInfoList)

    }

    @Test
    fun `getQuiz() returns list of QuestionInfo`() = runBlocking {

        // Arrange
        val quizResponse1 = QuizResponse(
            id = 1,
            question = "What is Fry's first name?",
            possibleAnswers = listOf("Fred", "Philip", "Will", "John"),
            correctAnswer = "Philip"
        )

        val quizResponse2 = QuizResponse(
            id = 2,
            question = "In 'Benders Big Score' what alien species scam the earth?",
            possibleAnswers = listOf(
                "Nibbloniens",
                "Omicrons",
                "Robots",
                "Nudest aliens",
                "Tentacals"
            ),
            correctAnswer = "Nudest aliens"
        )

        val expectedQuestionInfoList = listOf(
            QuestionInfo(
                id = 1,
                question = "What is Fry's first name?",
                possibleAnswers = listOf("Fred", "Philip", "Will", "John"),
                correctAnswer = "Philip"
            ),
            QuestionInfo(
                id = 2,
                question = "In 'Benders Big Score' what alien species scam the earth?",
                possibleAnswers = listOf(
                    "Nibbloniens",
                    "Omicrons",
                    "Robots",
                    "Nudest aliens",
                    "Tentacals"
                ),
                correctAnswer = "Nudest aliens"
            )
        )

        coEvery { apiService.getQuiz() } returns listOf(quizResponse1, quizResponse2)

        //Act
        val questionInfoList = futuramaRepository.getQuiz()
        //Assert
        assertEquals(questionInfoList, expectedQuestionInfoList)
    }
}
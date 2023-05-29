package com.thus.futurama.ui.character

import com.thus.futurama.domain.model.CharacterImage
import com.thus.futurama.domain.model.CharacterInfo
import com.thus.futurama.domain.model.CharacterName
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
class CharacterViewModelTest {

    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: CharacterViewModel
    private lateinit var futuramaRepository: FuturamaRepository

    @Before
    fun setup() {
        futuramaRepository = mockk()
        viewModel = CharacterViewModel(futuramaRepository, testDispatcher)
    }

    @Test
    fun `refresh should set charactersState to Normal when characters list is not empty`() {
        // Given
        val characterInfoList = listOf(
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
        coEvery { futuramaRepository.getCharacters() } returns characterInfoList

        // When
        viewModel.refresh()

        // Then
        assertEquals(CharactersState.Normal(characterInfoList), viewModel.charactersState.value)
    }

    @Test
    fun `refresh should set charactersState to Empty when characters list is empty`() {
        // Given
        val emptyCharacterInfoList = emptyList<CharacterInfo>()
        coEvery { futuramaRepository.getCharacters() } returns emptyCharacterInfoList

        // When
        viewModel.refresh()

        // Then
        assertEquals(CharactersState.Empty, viewModel.charactersState.value)
    }

    @Test
    fun `refresh should set charactersState to Error when an exception occurs`() {
        // Given
        val exception = Exception("error message")
        coEvery { futuramaRepository.getCharacters() } throws exception

        // When
        viewModel.refresh()

        // Then
        assertEquals(CharactersState.Error(exception), viewModel.charactersState.value)
    }
}
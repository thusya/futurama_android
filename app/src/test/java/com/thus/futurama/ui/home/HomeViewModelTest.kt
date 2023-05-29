package com.thus.futurama.ui.home

import com.thus.futurama.domain.model.ShowInfo
import com.thus.futurama.domain.repository.FuturamaRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private lateinit var futuramaRepository: FuturamaRepository
    private lateinit var homeViewModel: HomeViewModel
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        futuramaRepository = mockk(relaxed = true)
        homeViewModel = HomeViewModel(futuramaRepository, testDispatcher)
    }

    @Test
    fun `refresh sets homeState to Empty when response is empty`() = runTest {
        //Given
        val emptyResponse = emptyList<ShowInfo>()
        coEvery { futuramaRepository.getShowInfo() } returns emptyResponse

        //When
        homeViewModel.refresh()

        //Then
        assertEquals(HomeState.Empty, homeViewModel.homeState.value)

    }

    @Test
    fun `refresh sets homeState to Normal when response is not empty`() = runTest {
        //Given
        val showInfoList = listOf(ShowInfo("Synopsis", "Years Aired", emptyList(), 1))
        coEvery { futuramaRepository.getShowInfo() } returns showInfoList

        //When
        homeViewModel.refresh()

        //Then
        assertEquals(HomeState.Normal(showInfoList), homeViewModel.homeState.value)

    }

    @Test
    fun `refresh sets homeState to Error when an exception occurs`() = runTest {
        // Given
        val exception = Exception("Test Exception")
        coEvery { futuramaRepository.getShowInfo() } throws exception

        // When
        homeViewModel.refresh()

        // Then
        assertEquals(HomeState.Error(exception), homeViewModel.homeState.value)
    }
}
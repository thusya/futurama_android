package com.thus.futurama.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thus.futurama.domain.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    val homeState = mutableStateOf<HomeState>(HomeState.Loading)

    init {
        retry()
    }

    fun retry() {
        homeState.value = HomeState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = homeRepository.getHomeScreenData()

                if (response.isEmpty()) {
                    homeState.value = HomeState.Empty
                } else {
                    homeState.value = HomeState.Normal(response)
                }
            } catch (e: Exception) {
                homeState.value = HomeState.Error
            }

        }
    }

}
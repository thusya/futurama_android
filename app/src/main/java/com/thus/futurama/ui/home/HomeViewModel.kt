package com.thus.futurama.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thus.futurama.domain.repository.FuturamaRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(
    private val futuramaRepository: FuturamaRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    val homeState = mutableStateOf<HomeState>(HomeState.Loading)

    init {
        refresh()
    }

    fun refresh() {
        homeState.value = HomeState.Loading
        viewModelScope.launch(ioDispatcher) {
            try {
                val response = futuramaRepository.getShowInfo()

                if (response.isEmpty()) {
                    homeState.value = HomeState.Empty
                } else {
                    homeState.value = HomeState.Normal(response)
                }
            } catch (e: Exception) {
                homeState.value = HomeState.Error(e)
            }

        }
    }

}
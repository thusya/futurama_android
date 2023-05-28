package com.thus.futurama.ui.home

import com.thus.futurama.data.model.HomeScreenResponse

sealed class HomeState {
    object Loading: HomeState()
    object Error: HomeState()
    object Empty: HomeState()
    data class Normal(val homeResponse: List<HomeScreenResponse>): HomeState()
}

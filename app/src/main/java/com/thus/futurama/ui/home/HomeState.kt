package com.thus.futurama.ui.home

import com.thus.futurama.domain.model.ShowInfo

sealed class HomeState {
    object Loading : HomeState()
    object Empty : HomeState()
    data class Error(val e: Exception) : HomeState()
    data class Normal(val showInfoList: List<ShowInfo>) : HomeState()
}
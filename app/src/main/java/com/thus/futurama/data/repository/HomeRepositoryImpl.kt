package com.thus.futurama.data.repository

import com.thus.futurama.domain.repository.HomeRepository
import com.thus.futurama.data.model.HomeScreenResponse
import com.thus.futurama.data.network.ApiService

class HomeRepositoryImpl(private val apiService: ApiService) : HomeRepository {

    override suspend fun getHomeScreenData(): List<HomeScreenResponse> {
        return apiService.getHomeScreenData()
    }
}
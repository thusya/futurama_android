package com.thus.futurama.di

import com.thus.futurama.domain.repository.FuturamaRepository
import com.thus.futurama.data.network.ApiService
import com.thus.futurama.data.repository.FuturamaRepositoryImpl
import com.thus.futurama.ui.character.CharacterViewModel
import com.thus.futurama.ui.home.HomeViewModel
import com.thus.futurama.ui.quiz.QuizViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModules = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://api.sampleapis.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient())
            .build()
            .create(ApiService::class.java)
    }

    viewModel { HomeViewModel(get()) }
    viewModel { CharacterViewModel(get()) }
    viewModel { QuizViewModel(get()) }

    factory<FuturamaRepository> { FuturamaRepositoryImpl(get()) }
}
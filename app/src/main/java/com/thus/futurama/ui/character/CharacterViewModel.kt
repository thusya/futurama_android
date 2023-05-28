package com.thus.futurama.ui.character

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thus.futurama.data.model.CharacterResponse
import com.thus.futurama.domain.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    val charactersState = mutableStateOf<CharactersState>(CharactersState.Loading)
    var characterSelected: CharacterResponse? = null

    init {
        retry()
    }

    fun retry() {
        charactersState.value = CharactersState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = homeRepository.getCharacters()
                if (response.isEmpty()) {
                    charactersState.value = CharactersState.Empty
                } else {
                    charactersState.value = CharactersState.Normal(response)
                }
            } catch (ex: Exception) {
                charactersState.value = CharactersState.Error
            }
        }

    }
}
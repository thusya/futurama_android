package com.thus.futurama.ui.character

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thus.futurama.domain.model.CharacterInfo
import com.thus.futurama.domain.repository.FuturamaRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val futuramaRepository: FuturamaRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    val charactersState = mutableStateOf<CharactersState>(CharactersState.Loading)
    var characterDetailSelected: CharacterInfo? = null

    init {
        refresh()
    }

    fun refresh() {
        charactersState.value = CharactersState.Loading
        viewModelScope.launch(ioDispatcher) {
            try {
                val response = futuramaRepository.getCharacters()
                if (response.isEmpty()) {
                    charactersState.value = CharactersState.Empty
                } else {
                    charactersState.value = CharactersState.Normal(response)
                }
            } catch (ex: Exception) {
                charactersState.value = CharactersState.Error(ex)
            }
        }

    }
}
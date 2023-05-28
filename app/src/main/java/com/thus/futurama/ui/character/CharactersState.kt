package com.thus.futurama.ui.character

import com.thus.futurama.data.model.CharacterResponse

sealed class CharactersState {
    object Loading : CharactersState()
    object Error : CharactersState()
    object Empty : CharactersState()
    data class Normal(val characterResponse: List<CharacterResponse>) : CharactersState()
}

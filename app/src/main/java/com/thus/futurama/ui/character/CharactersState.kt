package com.thus.futurama.ui.character

import com.thus.futurama.domain.model.CharacterInfo

sealed class CharactersState {
    object Loading : CharactersState()
    object Empty : CharactersState()
    data class Error(val e: Exception) : CharactersState()
    data class Normal(val characterInfoList: List<CharacterInfo>) : CharactersState()
}

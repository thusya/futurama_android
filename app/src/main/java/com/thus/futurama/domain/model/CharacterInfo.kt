package com.thus.futurama.domain.model

data class CharacterInfo(
    val id: Int = 0,
    val name: CharacterName,
    val images: CharacterImage,
    val age: String = "",
    val gender: String = "",
    val species: String = "",
    val homePlanet: String = "",
    val occupation: String = "",
    val sayings: List<String> = emptyList()
) {
    fun getFullName() = name.first + name.middle + name.last
}

data class CharacterName(
    val first: String = "",
    val middle: String = "",
    val last: String = ""
)

data class CharacterImage(
    val headShot: String = "",
    val main: String = ""
)

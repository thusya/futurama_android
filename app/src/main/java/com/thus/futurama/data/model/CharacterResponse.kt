package com.thus.futurama.data.model

import com.squareup.moshi.Json

data class CharacterResponse(
    @field:Json(name = "name")
    val name: NameResponse,

    @field:Json(name = "images")
    val images: ImageResponse,

    @field:Json(name = "age")
    val age: String? = "",

    @field:Json(name = "gender")
    val gender: String? = "",

    @field:Json(name = "species")
    val species: String? = "",

    @field:Json(name = "homePlanet")
    val homePlanet: String? = "",

    @field:Json(name = "occupation")
    val occupation: String? = "",

    @field:Json(name = "sayings")
    val sayings: List<String>? = emptyList(),

    @field:Json(name = "id")
    val id: Int? = 0
)

data class NameResponse(
    @field:Json(name = "first")
    val first: String? = "",

    @field:Json(name = "middle")
    val middle: String? = "",

    @field:Json(name = "last")
    val last: String? = ""
)

data class ImageResponse(
    @field:Json(name = "head-shot")
    val headShot: String? = "",

    @field:Json(name = "main")
    val main: String? = ""
)

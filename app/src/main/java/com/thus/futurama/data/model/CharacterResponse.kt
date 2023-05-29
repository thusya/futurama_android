package com.thus.futurama.data.model

import com.squareup.moshi.Json
import com.thus.futurama.domain.model.CharacterImage
import com.thus.futurama.domain.model.CharacterInfo
import com.thus.futurama.domain.model.CharacterName

data class CharacterResponse(
    @field:Json(name = "name")
    val name: NameResponse? = null,

    @field:Json(name = "images")
    val images: ImageResponse? = null,

    @field:Json(name = "age")
    val age: String? = null,

    @field:Json(name = "gender")
    val gender: String? = null,

    @field:Json(name = "species")
    val species: String? = null,

    @field:Json(name = "homePlanet")
    val homePlanet: String? = null,

    @field:Json(name = "occupation")
    val occupation: String? = null,

    @field:Json(name = "sayings")
    val sayings: List<String>? = null,

    @field:Json(name = "id")
    val id: Int? = null
)

data class NameResponse(
    @field:Json(name = "first")
    val first: String? = null,

    @field:Json(name = "middle")
    val middle: String? = null,

    @field:Json(name = "last")
    val last: String? = null
)

data class ImageResponse(
    @field:Json(name = "head-shot")
    val headShot: String? = null,

    @field:Json(name = "main")
    val main: String? = null
)

fun CharacterResponse.toCharacterInfo() = CharacterInfo(
    id = id ?: 0,
    name = CharacterName(
        first = name?.first.orEmpty(),
        middle = name?.middle.orEmpty(),
        last = name?.last.orEmpty()
    ),
    images = CharacterImage(
        headShot = images?.headShot.orEmpty(),
        main = images?.main.orEmpty()
    ),
    age = age.orEmpty(),
    gender = gender.orEmpty(),
    species = species.orEmpty(),
    homePlanet = homePlanet.orEmpty(),
    occupation = occupation.orEmpty(),
    sayings = sayings.orEmpty()
)

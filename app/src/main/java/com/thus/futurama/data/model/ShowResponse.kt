package com.thus.futurama.data.model

import com.squareup.moshi.Json
import com.thus.futurama.domain.model.CreatorInfo
import com.thus.futurama.domain.model.ShowInfo

data class ShowResponse(

    @field:Json(name = "synopsis")
    val synopsis: String? = null,

    @field:Json(name = "yearsAired")
    val yearsAired: String? = null,

    @field:Json(name = "creators")
    val creators: List<CreatorResponse>? = null,

    @field:Json(name = "id")
    val id: Int? = null
)

data class CreatorResponse(

    @field:Json(name = "name")
    val name: String? = null,

    @field:Json(name = "url")
    val url: String? = null
)

fun ShowResponse.toShowInfo() = ShowInfo(
    synopsis = synopsis.orEmpty(),
    yearsAired = yearsAired.orEmpty(),
    creators = creators?.map { creator ->
        CreatorInfo(
            name = creator.name.orEmpty(),
            url = creator.url.orEmpty()
        )
    }.orEmpty(),
    id = id ?: 0
)

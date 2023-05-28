package com.thus.futurama.data.model

import com.squareup.moshi.Json

data class HomeScreenResponse(
    @field:Json(name = "synopsis") val synopsis: String? = "",
    @field:Json(name = "yearsAired") val yearsAired: String? = "",
    @field:Json(name = "creators") val creators: List<CreatorResponse>? = emptyList(),
    @field:Json(name = "id") val id: Int? = 0
)

data class CreatorResponse(
    @field:Json(name = "name") val name: String? = "",
    @field:Json(name = "url") val url: String? = ""
)

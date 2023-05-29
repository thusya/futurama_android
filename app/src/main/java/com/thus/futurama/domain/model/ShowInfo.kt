package com.thus.futurama.domain.model

data class ShowInfo(
    val synopsis: String = "",
    val yearsAired: String = "",
    val creators: List<CreatorInfo> = emptyList(),
    val id: Int = 0
)

data class CreatorInfo(
    val name: String = "",
    val url: String = ""
)
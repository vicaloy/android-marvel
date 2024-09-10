package com.example.marvel.infra.dto

import com.example.comics.domain.model.Comics
import com.example.comics.infra.dto.ComicsData
import com.example.comics.infra.dto.ComicsResult
import com.example.comics.infra.dto.Thumbnail
import com.example.marvel.domain.model.Characters
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharactersResponse(
    val code: Long,
    val status: String,
    val copyright: String,
    @Json(name = "data")
    val charactersData: CharactersData,
)

@JsonClass(generateAdapter = true)
data class CharactersData(
    val offset: Long,
    val limit: Long,
    val total: Long,
    val count: Long,
    @Json(name = "results")
    val charactersResults: List<CharactersResult>,
)

@JsonClass(generateAdapter = true)
data class CharactersResult(
    val id: Long,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: Thumbnail,
) {
    fun mapToCharacter() = Characters(
        id = id,
        name = name,
        thumbnail = thumbnail.mapToThumbnail()
    )
}

@JsonClass(generateAdapter = true)
data class Thumbnail(
    val path: String,
    val extension: String,
) {
    fun mapToThumbnail() = "$path.$extension"
}
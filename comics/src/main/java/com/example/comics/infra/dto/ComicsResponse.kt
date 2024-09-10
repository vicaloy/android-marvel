package com.example.comics.infra.dto

import com.example.comics.domain.model.Comics
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ComicsResponse(
    val code: Long,
    val status: String,
    val copyright: String,
    @Json(name = "data")
    val comicsData: ComicsData,
)

@JsonClass(generateAdapter = true)
data class ComicsData(
    val offset: Long,
    val limit: Long,
    val total: Long,
    val count: Long,
    @Json(name = "results")
    val comicsResults: List<ComicsResult>,
)

@JsonClass(generateAdapter = true)
data class ComicsResult(
    val id: Long,
    val title: String,
    val description: String?,
    val modified: String,
    val thumbnail: Thumbnail,
) {
    fun mapToComic() = Comics(
        id = id,
        name = title,
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
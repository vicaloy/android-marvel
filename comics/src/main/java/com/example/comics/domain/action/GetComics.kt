package com.example.comics.domain.action

import com.example.comics.domain.repository.ComicsRepository

class GetComics(private val comicsRepository: ComicsRepository) {
    suspend operator fun invoke(
        ts: String,
        apikey: String,
        hash: String,
        query: String?,
        limit: Int,
        offset: Int
    ) = comicsRepository.getAll(
        ts,
        apikey,
        hash,
        query,
        limit,
        offset
    )
}
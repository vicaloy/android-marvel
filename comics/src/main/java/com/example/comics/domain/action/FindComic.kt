package com.example.comics.domain.action

import com.example.comics.domain.repository.ComicsRepository

class FindComic(private val comicsRepository: ComicsRepository) {
    suspend operator fun invoke(
        ts: String,
        apikey: String,
        hash: String,
        id: Long
    ) = comicsRepository.find(
        ts,
        apikey,
        hash,
        id
    )
}
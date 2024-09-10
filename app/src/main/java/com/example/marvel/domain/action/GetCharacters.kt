package com.example.marvel.domain.action

import com.example.marvel.domain.repository.CharactersRepository

class GetCharacters(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(
        ts: String,
        apikey: String,
        hash: String,
        query: String?,
        limit: Int,
        offset: Int
    ) = charactersRepository.getAll(
        ts,
        apikey,
        hash,
        query,
        limit,
        offset
    )
}
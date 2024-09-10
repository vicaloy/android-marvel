package com.example.marvel.domain.action

import com.example.marvel.domain.repository.CharactersRepository

class FindCharacter(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(
        ts: String,
        apikey: String,
        hash: String,
        id: Long
    ) = charactersRepository.find(
        ts,
        apikey,
        hash,
        id
    )
}
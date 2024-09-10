package com.example.marvel.infra.repository

import com.example.marvel.domain.model.Characters
import com.example.marvel.domain.repository.CharactersRepository
import com.example.marvel.infra.client.CharactersClient

class CharactersRemoteRepository(private val charactersClient: CharactersClient) :
    CharactersRepository {
    override suspend fun getAll(
        ts: String,
        apikey: String,
        hash: String,
        query: String?,
        limit: Int,
        offset: Int
    ): List<Characters> {
        return charactersClient.getCharacters(
            ts,
            apikey,
            hash,
            query,
            limit,
            offset
        ).charactersData.charactersResults.map { it.mapToCharacter() }
    }

    override suspend fun find(ts: String, apikey: String, hash: String, id: Long): Characters? {
        return charactersClient.findCharacter(
            id,
            ts,
            apikey,
            hash
        )?.charactersData?.charactersResults?.firstOrNull()?.mapToCharacter()
    }
}
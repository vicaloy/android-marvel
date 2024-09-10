package com.example.marvel.infra.repository

import com.example.comics.domain.model.Comics
import com.example.comics.domain.repository.ComicsRepository
import com.example.comics.infra.client.ComicsClient

class ComicsRemoteRepository(private val comicsClient: ComicsClient) :
    ComicsRepository {
    override suspend fun getAll(
        ts: String,
        apikey: String,
        hash: String,
        query: String?,
        limit: Int,
        offset: Int
    ): List<Comics> {
        return comicsClient.getCharacters(
            ts,
            apikey,
            hash,
            query,
            limit,
            offset
        ).comicsData.comicsResults.map { it.mapToComic() }
    }

    override suspend fun find(ts: String, apikey: String, hash: String, id: Long): Comics? {
        return comicsClient.findCharacter(
            id,
            ts,
            apikey,
            hash
        )?.comicsData?.comicsResults?.firstOrNull()?.mapToComic()
    }
}
package com.example.marvel.domain.repository

import com.example.marvel.domain.model.Characters

interface CharactersRepository {
    suspend fun getAll(
        ts: String,
        apikey: String,
        hash: String,
        query: String?,
        limit: Int,
        offset: Int
    ): List<Characters>

    suspend fun find(
        ts: String,
        apikey: String,
        hash: String,
        id: Long
    ): Characters?
}
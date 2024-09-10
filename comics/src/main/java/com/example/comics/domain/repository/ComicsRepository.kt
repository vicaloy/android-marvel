package com.example.comics.domain.repository

import com.example.comics.domain.model.Comics

interface ComicsRepository {
    suspend fun getAll(
        ts: String,
        apikey: String,
        hash: String,
        query: String?,
        limit: Int,
        offset: Int
    ): List<Comics>

    suspend fun find(
        ts: String,
        apikey: String,
        hash: String,
        id: Long
    ): Comics?
}
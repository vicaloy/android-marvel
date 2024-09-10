package com.example.comics.infra.client

import com.example.comics.infra.dto.ComicsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ComicsClient {
    @GET("/v1/public/comics")
    suspend fun getCharacters(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("nameStartsWith") query: String? = null,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ComicsResponse

    @GET("/v1/public/comics/{id}")
    suspend fun findCharacter(
        @Path("id") id: Long,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): ComicsResponse?
}
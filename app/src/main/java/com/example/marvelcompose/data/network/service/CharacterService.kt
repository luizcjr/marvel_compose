package com.example.marvelcompose.data.network.service

import com.example.marvelcompose.data.network.response.DataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface CharacterService {

    @GET("characters")
    suspend fun getCharacters(
        @QueryMap
        queries: Map<String, String>
    ): DataWrapperResponse
}
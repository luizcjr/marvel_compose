package com.example.marvelcompose.data.network.service

import com.example.marvelcompose.data.network.response.CharacterResponse
import com.example.marvelcompose.data.network.response.DataWrapperResponse
import com.example.marvelcompose.data.network.response.DetailsReponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface CharacterService {

    @GET("characters")
    suspend fun getCharacters(
        @QueryMap
        queries: Map<String, String>
    ): DataWrapperResponse<CharacterResponse>

    @GET("characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") id: Int
    ): DataWrapperResponse<CharacterResponse>

    @GET("characters/{characterId}/comics")
    suspend fun getComicsByCharacterId(
        @Path("characterId") id: Int,
        @QueryMap
        queries: Map<String, String>
    ): DataWrapperResponse<DetailsReponse>

    @GET("characters/{characterId}/series")
    suspend fun getSeriesByCharacterId(
        @Path("characterId") id: Int,
        @QueryMap
        queries: Map<String, String>
    ): DataWrapperResponse<DetailsReponse>

    @GET("characters/{characterId}/events")
    suspend fun getEventsByCharacterId(
        @Path("characterId") id: Int,
        @QueryMap
        queries: Map<String, String>
    ): DataWrapperResponse<DetailsReponse>
}
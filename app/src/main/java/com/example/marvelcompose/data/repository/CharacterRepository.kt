package com.example.marvelcompose.data.repository

import com.example.marvelcompose.data.network.response.CharacterResponse
import com.example.marvelcompose.data.network.response.DataWrapperResponse
import com.example.marvelcompose.data.network.response.DetailsReponse

interface CharacterRepository {
    suspend fun fetchCharacters(queries: Map<String, String>): DataWrapperResponse<CharacterResponse>
    suspend fun fetchCharacterById(id: Int): DataWrapperResponse<CharacterResponse>
    suspend fun fetchComicsByCharacterId(queries: Map<String, String>, id: Int): DataWrapperResponse<DetailsReponse>
    suspend fun fetchSeriesByCharacterId(queries: Map<String, String>, id: Int): DataWrapperResponse<DetailsReponse>
    suspend fun fetchEventsByCharacterId(queries: Map<String, String>, id: Int): DataWrapperResponse<DetailsReponse>
}
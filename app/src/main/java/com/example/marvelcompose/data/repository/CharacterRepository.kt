package com.example.marvelcompose.data.repository

import com.example.marvelcompose.data.network.response.CharacterResponse
import com.example.marvelcompose.data.network.response.DataWrapperResponse

interface CharacterRepository {
    suspend fun fetchCharacters(queries: Map<String, String>): DataWrapperResponse<CharacterResponse>
    suspend fun fetchCharacterById(id: Int): DataWrapperResponse<CharacterResponse>
}
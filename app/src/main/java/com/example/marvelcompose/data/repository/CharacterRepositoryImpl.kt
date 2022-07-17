package com.example.marvelcompose.data.repository

import com.example.marvelcompose.data.network.response.DataWrapperResponse
import com.example.marvelcompose.data.network.service.CharacterService

class CharacterRepositoryImpl(
    private val characterService: CharacterService
) : CharacterRepository {
    override suspend fun fetchCharacters(queries: Map<String, String>): DataWrapperResponse =
        characterService.getCharacters(queries)
}
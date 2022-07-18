package com.example.marvelcompose.data.repository

import com.example.marvelcompose.data.network.response.CharacterResponse
import com.example.marvelcompose.data.network.response.DataWrapperResponse
import com.example.marvelcompose.data.network.service.CharacterService

class CharacterRepositoryImpl(
    private val characterService: CharacterService
) : CharacterRepository {
    override suspend fun fetchCharacters(queries: Map<String, String>): DataWrapperResponse<CharacterResponse> =
        characterService.getCharacters(queries)

    override suspend fun fetchCharacterById(id: Int): DataWrapperResponse<CharacterResponse> =
        characterService.getCharactersById(id)
}
package com.example.marvelcompose.data.repository

import com.example.marvelcompose.data.network.response.CharacterResponse
import com.example.marvelcompose.data.network.response.DataWrapperResponse
import com.example.marvelcompose.data.network.response.DetailsReponse
import com.example.marvelcompose.data.network.service.CharacterService

class CharacterRepositoryImpl(
    private val characterService: CharacterService
) : CharacterRepository {
    override suspend fun fetchCharacters(queries: Map<String, String>): DataWrapperResponse<CharacterResponse> =
        characterService.getCharacters(queries)

    override suspend fun fetchCharacterById(id: Int): DataWrapperResponse<CharacterResponse> =
        characterService.getCharacterById(id)

    override suspend fun fetchComicsByCharacterId(
        queries: Map<String, String>,
        id: Int
    ): DataWrapperResponse<DetailsReponse> = characterService.getComicsByCharacterId(id, queries)

    override suspend fun fetchSeriesByCharacterId(
        queries: Map<String, String>,
        id: Int
    ): DataWrapperResponse<DetailsReponse> = characterService.getSeriesByCharacterId(id, queries)

    override suspend fun fetchEventsByCharacterId(
        queries: Map<String, String>,
        id: Int
    ): DataWrapperResponse<DetailsReponse> = characterService.getEventsByCharacterId(id, queries)
}
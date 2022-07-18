package com.example.marvelcompose.domain.usecase

import com.example.marvelcompose.data.network.response.CharacterResponse
import com.example.marvelcompose.data.network.response.DataContainerResponse
import com.example.marvelcompose.data.network.response.DataWrapperResponse
import com.example.marvelcompose.data.network.response.ThumbnailResponse

object CharactersUseCaseImplTestMock {

    private val thumbnailResponse = ThumbnailResponse("image", ".png")
    private val characterReponseSpider = CharacterResponse(1, "Spider-Man", thumbnailResponse)
    private val characterReponseIronMan = CharacterResponse(2, "Iron-Man", thumbnailResponse)
    private val dataContainerResponse =
        DataContainerResponse(1, 10, listOf(characterReponseSpider, characterReponseIronMan))
    val dataWrapperResponse = DataWrapperResponse("", dataContainerResponse)
}
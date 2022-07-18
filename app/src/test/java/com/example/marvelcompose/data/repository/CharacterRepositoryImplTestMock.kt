package com.example.marvelcompose.data.repository

import com.example.marvelcompose.data.network.response.CharacterResponse
import com.example.marvelcompose.data.network.response.DataContainerResponse
import com.example.marvelcompose.data.network.response.DataWrapperResponse
import com.example.marvelcompose.data.network.response.ThumbnailResponse

object CharacterRepositoryImplTestMock {

    private val thumbnailResponse = ThumbnailResponse("image", ".png")
    private val characterReponseSpider =
        CharacterResponse(1, "Spider-Man", "description", thumbnailResponse)
    private val characterReponseIronMan =
        CharacterResponse(2, "Iron-Man", "description", thumbnailResponse)
    private val dataContainerResponse =
        DataContainerResponse(1, 10, listOf(characterReponseSpider, characterReponseIronMan))
    val dataWrapperResponse = DataWrapperResponse("", dataContainerResponse)
}
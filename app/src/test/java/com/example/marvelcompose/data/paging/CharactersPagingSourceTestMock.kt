package com.example.marvelcompose.data.paging

import com.example.marvelcompose.data.network.response.CharacterResponse
import com.example.marvelcompose.data.network.response.DataContainerResponse
import com.example.marvelcompose.data.network.response.DataWrapperResponse
import com.example.marvelcompose.data.network.response.ThumbnailResponse
import com.example.marvelcompose.domain.model.Character

object CharactersPagingSourceTestMock {

    private val thumbnailResponse = ThumbnailResponse("image", "png")
    private val characterReponseSpider = CharacterResponse(1, "Spider-Man", thumbnailResponse)
    private val characterReponseIronMan = CharacterResponse(2, "Iron-Man", thumbnailResponse)
    private val dataContainerResponse =
        DataContainerResponse(0, 2, listOf(characterReponseSpider, characterReponseIronMan))
    val dataWrapperResponse = DataWrapperResponse("", dataContainerResponse)
    private val characterSpider = Character(1, "Spider-Man", "image.png")
    private val characterIron = Character(2, "Iron-Man", "image.png")
    val characterList = listOf(characterSpider, characterIron)
}
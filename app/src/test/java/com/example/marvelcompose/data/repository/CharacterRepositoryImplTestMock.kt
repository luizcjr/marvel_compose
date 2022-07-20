package com.example.marvelcompose.data.repository

import com.example.marvelcompose.data.network.response.*

object CharacterRepositoryImplTestMock {

    private val thumbnailResponse = ThumbnailResponse("image", ".png")

    private val characterReponseSpider =
        CharacterResponse(1, "Spider-Man", "description", thumbnailResponse)
    private val characterReponseIronMan =
        CharacterResponse(2, "Iron-Man", "description", thumbnailResponse)
    private val dataContainerResponse =
        DataContainerResponse(1, 10, listOf(characterReponseSpider, characterReponseIronMan))
    val dataWrapperResponse = DataWrapperResponse("", dataContainerResponse)

    private val comicsResponse = DetailsReponse(1, "Comics 1", thumbnailResponse)
    private val comicsResponse2 = DetailsReponse(2, "Comics 2", thumbnailResponse)
    private val comicsDataContainerResponse =
        DataContainerResponse(1, 10, listOf(comicsResponse, comicsResponse2))
    val comicsDataWrapperResponse = DataWrapperResponse("", comicsDataContainerResponse)

    private val seriesResponse = DetailsReponse(1, "Series 1", thumbnailResponse)
    private val seriesResponse2 = DetailsReponse(2, "Series 2", thumbnailResponse)
    private val seriesDataContainerResponse =
        DataContainerResponse(1, 10, listOf(seriesResponse, seriesResponse2))
    val seriesDataWrapperResponse = DataWrapperResponse("", seriesDataContainerResponse)

    private val storiesResponse = DetailsReponse(1, "Stories 1", thumbnailResponse)
    private val storiesResponse2 = DetailsReponse(2, "Stories 2", thumbnailResponse)
    private val storiesDataContainerResponse =
        DataContainerResponse(1, 10, listOf(storiesResponse, storiesResponse2))
    val storiesDataWrapperResponse = DataWrapperResponse("", storiesDataContainerResponse)
}
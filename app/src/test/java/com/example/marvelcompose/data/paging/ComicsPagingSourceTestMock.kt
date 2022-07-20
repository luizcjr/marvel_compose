package com.example.marvelcompose.data.paging

import com.example.marvelcompose.data.network.response.DataContainerResponse
import com.example.marvelcompose.data.network.response.DataWrapperResponse
import com.example.marvelcompose.data.network.response.DetailsReponse
import com.example.marvelcompose.data.network.response.ThumbnailResponse
import com.example.marvelcompose.domain.model.Details

object ComicsPagingSourceTestMock {

    private val thumbnailResponse = ThumbnailResponse("image", "png")
    private val comicsResponse =
        DetailsReponse(1, "Comics 1", thumbnailResponse)
    private val comicsResponse2 =
        DetailsReponse(2, "Comics 2", thumbnailResponse)
    private val comicsDataContainerResponse =
        DataContainerResponse(
            1, 10, listOf(
                comicsResponse,
                comicsResponse2
            )
        )
    val comicsDataWrapperResponse = DataWrapperResponse(
        "",
        comicsDataContainerResponse
    )
    private val comics = Details(1, "Comics 1", "image.png")
    private val comics2 = Details(2, "Comics 2", "image.png")
    val detailsList = listOf(comics, comics2)
}
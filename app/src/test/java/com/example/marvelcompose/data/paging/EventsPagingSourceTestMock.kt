package com.example.marvelcompose.data.paging

import com.example.marvelcompose.data.network.response.DataContainerResponse
import com.example.marvelcompose.data.network.response.DataWrapperResponse
import com.example.marvelcompose.data.network.response.DetailsReponse
import com.example.marvelcompose.data.network.response.ThumbnailResponse
import com.example.marvelcompose.domain.model.Details

object EventsPagingSourceTestMock {

    private val thumbnailResponse = ThumbnailResponse("image", "png")
    private val storiesResponse =
        DetailsReponse(1, "Stories 1", thumbnailResponse)
    private val storiesResponse2 =
        DetailsReponse(2, "Stories 2", thumbnailResponse)
    private val storiesDataContainerResponse =
        DataContainerResponse(
            1, 10, listOf(
                storiesResponse,
                storiesResponse2
            )
        )
    val storiesDataWrapperResponse = DataWrapperResponse(
        "",
        storiesDataContainerResponse
    )
    private val stories = Details(1, "Stories 1", "image.png")
    private val stories2 = Details(2, "Stories 2", "image.png")
    val storiesList = listOf(stories, stories2)
}
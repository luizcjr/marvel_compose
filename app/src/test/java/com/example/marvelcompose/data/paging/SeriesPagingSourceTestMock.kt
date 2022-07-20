package com.example.marvelcompose.data.paging

import com.example.marvelcompose.data.network.response.DataContainerResponse
import com.example.marvelcompose.data.network.response.DataWrapperResponse
import com.example.marvelcompose.data.network.response.DetailsReponse
import com.example.marvelcompose.data.network.response.ThumbnailResponse
import com.example.marvelcompose.domain.model.Details

object SeriesPagingSourceTestMock {

    private val thumbnailResponse = ThumbnailResponse("image", "png")
    private val seriesResponse =
        DetailsReponse(1, "Series 1", thumbnailResponse)
    private val seriesResponse2 =
        DetailsReponse(2, "Series 2", thumbnailResponse)
    private val seriesDataContainerResponse =
        DataContainerResponse(
            1, 10, listOf(
                seriesResponse,
                seriesResponse2
            )
        )
    val seriesDataWrapperResponse = DataWrapperResponse(
        "",
        seriesDataContainerResponse
    )
    private val series = Details(1, "Series 1", "image.png")
    private val series2 = Details(2, "Series 2", "image.png")
    val seriesList = listOf(series, series2)
}
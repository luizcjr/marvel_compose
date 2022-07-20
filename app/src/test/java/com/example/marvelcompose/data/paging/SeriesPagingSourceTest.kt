package com.example.marvelcompose.data.paging

import androidx.paging.PagingSource
import com.example.marvelcompose.data.paging.SeriesPagingSourceTestMock.seriesDataWrapperResponse
import com.example.marvelcompose.data.paging.SeriesPagingSourceTestMock.seriesList
import com.example.marvelcompose.data.repository.CharacterRepository
import com.example.marvelcompose.domain.model.Details
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class SeriesPagingSourceTest {

    private val remote = mock<CharacterRepository>()
    private val seriesPagingSource = SeriesPagingSource(remote, 1)

    @Test
    fun `should return a success load result when load is called`() = runBlocking {
        whenever(remote.fetchSeriesByCharacterId(any(), any()))
            .doReturn(seriesDataWrapperResponse)

        val result = seriesPagingSource.load(
            PagingSource.LoadParams.Refresh(
                null,
                loadSize = 2,
                false
            )
        )

        val expected = seriesList

        assertEquals(
            PagingSource.LoadResult.Page(
                data = expected,
                prevKey = null,
                nextKey = 21
            ),
            result
        )
    }

    @Test
    fun `should return a error load result when load is called`() = runBlocking {
        val exception = RuntimeException()
        whenever(remote.fetchSeriesByCharacterId(any(), any()))
            .thenThrow(exception)

        val result = seriesPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        assertEquals(
            PagingSource.LoadResult.Error<Int, Details>(exception),
            result
        )
    }
}
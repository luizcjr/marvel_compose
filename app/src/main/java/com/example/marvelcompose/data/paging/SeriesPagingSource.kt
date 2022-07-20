package com.example.marvelcompose.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.marvelcompose.data.network.response.toDetailModel
import com.example.marvelcompose.data.repository.CharacterRepository
import com.example.marvelcompose.domain.model.Details

class SeriesPagingSource(
    private val characterRepository: CharacterRepository,
    private val id: Int
) : PagingSource<Int, Details>() {

    override fun getRefreshKey(state: PagingState<Int, Details>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Details> {
        return try {
            val offset = params.key ?: 0

            val queries = hashMapOf(
                "offset" to offset.toString()
            )

            val response = characterRepository.fetchSeriesByCharacterId(queries, id).data

            val responseOffset = response.offset
            val totalDetails = response.total

            LoadResult.Page(
                data = response.results.map { it.toDetailModel() },
                prevKey = null,
                nextKey = if (responseOffset < totalDetails) {
                    responseOffset + LIMIT
                } else null
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    companion object {
        private const val LIMIT = 20
    }
}
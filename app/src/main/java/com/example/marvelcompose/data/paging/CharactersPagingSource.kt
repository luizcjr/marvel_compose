package com.example.marvelcompose.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.marvelcompose.data.network.response.toCharacterModel
import com.example.marvelcompose.data.repository.CharacterRepository
import com.example.marvelcompose.domain.model.Character

class CharactersPagingSource(
    private val characterRepository: CharacterRepository,
    private val query: String
) : PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val offset = params.key ?: 0

            val queries = hashMapOf(
                "offset" to offset.toString()
            )

            if (query.isNotEmpty()) {
                queries["nameStartsWith"] = query
            }

            val response = characterRepository.fetchCharacters(queries).data

            val responseOffset = response.offset
            val totalCharacters = response.total

            LoadResult.Page(
                data = response.results.map { it.toCharacterModel() },
                prevKey = null,
                nextKey = if (responseOffset < totalCharacters) {
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
package com.example.marvelcompose.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.marvelcompose.data.network.response.toCharacterModel
import com.example.marvelcompose.data.paging.CharactersPagingSource
import com.example.marvelcompose.data.paging.ComicsPagingSource
import com.example.marvelcompose.data.paging.SeriesPagingSource
import com.example.marvelcompose.data.paging.EventsPagingSource
import com.example.marvelcompose.data.repository.CharacterRepository
import com.example.marvelcompose.domain.model.Character
import com.example.marvelcompose.domain.model.Details
import kotlinx.coroutines.flow.Flow
import java.io.IOException

class CharactersUseCaseImpl(
    private val repository: CharacterRepository
) : CharactersUseCase {
    override fun getCharacters(query: String): Flow<PagingData<Character>> {
        return Pager(getPageConfig()) {
            CharactersPagingSource(repository, query)
        }.flow
    }

    @Throws(IOException::class)
    override suspend fun getCharacterById(id: Int): List<Character> = try {
        repository.fetchCharacterById(id).data.results.map {
            it.toCharacterModel()
        }
    } catch (e: Exception) {
        throw IOException()
    }

    override fun getComics(id: Int): Flow<PagingData<Details>> {
        return Pager(getPageConfig()) {
            ComicsPagingSource(repository, id)
        }.flow
    }

    override fun getSeries(id: Int): Flow<PagingData<Details>> {
        return Pager(getPageConfig()) {
            SeriesPagingSource(repository, id)
        }.flow
    }

    override fun getEvents(id: Int): Flow<PagingData<Details>> {
        return Pager(getPageConfig()) {
            EventsPagingSource(repository, id)
        }.flow
    }

    private fun getPageConfig() = PagingConfig(
        pageSize = 20
    )
}
package com.example.marvelcompose.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.marvelcompose.data.paging.CharactersPagingSource
import com.example.marvelcompose.data.repository.CharacterRepository
import com.example.marvelcompose.domain.model.Character
import kotlinx.coroutines.flow.Flow

class CharactersUseCaseImpl(
    private val repository: CharacterRepository
) : CharactersUseCase {
    override fun getCharacters(query: String): Flow<PagingData<Character>> {
        return Pager(getPageConfig()) {
            CharactersPagingSource(repository, query)
        }.flow
    }

    private fun getPageConfig() = PagingConfig(
        pageSize = 20
    )
}
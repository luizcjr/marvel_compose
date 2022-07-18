package com.example.marvelcompose.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.marvelcompose.data.network.response.toCharacterModel
import com.example.marvelcompose.data.paging.CharactersPagingSource
import com.example.marvelcompose.data.repository.CharacterRepository
import com.example.marvelcompose.domain.model.Character
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import kotlin.jvm.Throws

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

    private fun getPageConfig() = PagingConfig(
        pageSize = 20
    )
}
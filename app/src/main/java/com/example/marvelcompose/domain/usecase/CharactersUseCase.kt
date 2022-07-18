package com.example.marvelcompose.domain.usecase

import androidx.paging.PagingData
import com.example.marvelcompose.domain.model.Character
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import kotlin.jvm.Throws

interface CharactersUseCase {
    fun getCharacters(query: String): Flow<PagingData<Character>>

    @Throws(IOException::class)
    suspend fun getCharacterById(id: Int): List<Character>
}
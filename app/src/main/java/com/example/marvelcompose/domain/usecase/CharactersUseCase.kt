package com.example.marvelcompose.domain.usecase

import androidx.paging.PagingData
import com.example.marvelcompose.domain.model.Character
import com.example.marvelcompose.domain.model.Details
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import kotlin.jvm.Throws

interface CharactersUseCase {
    fun getCharacters(query: String): Flow<PagingData<Character>>
    fun getComics(id: Int): Flow<PagingData<Details>>
    fun getSeries(id: Int): Flow<PagingData<Details>>
    fun getEvents(id: Int): Flow<PagingData<Details>>
    suspend fun getCharacterById(id: Int): List<Character>
}
package com.example.marvelcompose.domain.usecase

import androidx.paging.PagingData
import com.example.marvelcompose.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharactersUseCase {
    fun getCharacters(query: String): Flow<PagingData<Character>>
}
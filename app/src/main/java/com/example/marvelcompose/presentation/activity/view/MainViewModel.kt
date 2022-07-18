package com.example.marvelcompose.presentation.activity.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.marvelcompose.domain.model.Character
import com.example.marvelcompose.domain.usecase.CharactersUseCase
import kotlinx.coroutines.flow.Flow

class MainViewModel(
    private val useCase: CharactersUseCase
) : ViewModel() {

    fun getAllCharacters(query: String): Flow<PagingData<Character>> = useCase.getCharacters(query).cachedIn(viewModelScope)
}
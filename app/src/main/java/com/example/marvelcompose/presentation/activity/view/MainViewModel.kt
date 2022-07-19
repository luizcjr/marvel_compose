package com.example.marvelcompose.presentation.activity.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.marvelcompose.domain.model.Character
import com.example.marvelcompose.domain.usecase.CharactersUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(
    private val useCase: CharactersUseCase
) : ViewModel() {

    private val _character = MutableLiveData<List<Character>>()
    val character: LiveData<List<Character>>
        get() = _character

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    fun getAllCharacters(query: String): Flow<PagingData<Character>> =
        useCase.getCharacters(query).cachedIn(viewModelScope)

    fun getCharacterById(id: Int) {
        viewModelScope.launch {
            try {
                _loading.postValue(true)
                _character.postValue(useCase.getCharacterById(id))
            } catch (e: Exception) {
                throw IOException()
            } finally {
                _loading.postValue(false)
            }
        }
    }
}
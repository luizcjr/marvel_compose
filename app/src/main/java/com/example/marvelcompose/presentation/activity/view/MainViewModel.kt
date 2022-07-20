package com.example.marvelcompose.presentation.activity.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.marvelcompose.domain.model.Character
import com.example.marvelcompose.domain.usecase.CharactersUseCase
import com.example.marvelcompose.presentation.activity.view.model.CharacterDetails
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(
    private val useCase: CharactersUseCase
) : ViewModel() {

    private val _characterDetails = MutableLiveData<CharacterDetails>()
    val characterDetails: LiveData<CharacterDetails>
        get() = _characterDetails

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    fun getAllCharacters(query: String): Flow<PagingData<Character>> =
        useCase.getCharacters(query)

    fun getCharacterDetailsById(id: Int) {
        viewModelScope.launch {
            try {
                _loading.postValue(true)
                async {
                    _characterDetails.postValue(
                        CharacterDetails(
                            useCase.getCharacterById(id),
                            useCase.getComics(id),
                            useCase.getSeries(id),
                            useCase.getEvents(id)
                        )
                    )
                }
            } catch (e: Exception) {
                throw IOException()
            } finally {
                _loading.postValue(false)
            }
        }
    }
}
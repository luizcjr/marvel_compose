package com.example.marvelcompose.presentation.activity.view

import androidx.lifecycle.Observer
import androidx.paging.PagingData
import com.example.marvelcompose.MainCoroutineRule
import com.example.marvelcompose.domain.model.Character
import com.example.marvelcompose.domain.usecase.CharactersUseCase
import com.example.marvelcompose.presentation.activity.view.MainViewModelTestMock.characterReponseIronMan
import com.example.marvelcompose.presentation.activity.view.MainViewModelTestMock.characterReponseSpider
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val useCase = mock<CharactersUseCase>()
    private val observer = mock<Observer<List<Character>>>()
    private lateinit var viewModel: MainViewModel

    private val pagingDataCharacters = PagingData.from(
        listOf(
            characterReponseSpider,
            characterReponseIronMan
        )
    )

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        viewModel = MainViewModel(useCase)
        viewModel.character.observeForever(observer)
    }

//    @ExperimentalCoroutinesApi
//    @Test
//    fun `should validate the paging data object values when calling charactersPagingData`() =
//        runBlocking {
//            whenever(useCase.getCharacters("")).doReturn(flowOf(pagingDataCharacters))
//            val result = viewModel.getAllCharacters("")
//            assertEquals(1, result.count())
//        }

    @ExperimentalCoroutinesApi
    @Test
    fun `when observe live data, then call get character by id, then return character detail`() =
        runBlocking {
            whenever(useCase.getCharacterById(1)).doReturn(listOf(characterReponseSpider))
            viewModel.getCharacterById(1)
            verify(observer).onChanged(listOf(characterReponseSpider))
        }
}
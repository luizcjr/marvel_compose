package com.example.marvelcompose.presentation.activity.view

import androidx.paging.PagingData
import com.example.marvelcompose.MainCoroutineRule
import com.example.marvelcompose.domain.usecase.CharactersUseCase
import com.example.marvelcompose.presentation.activity.view.MainViewModelTestMock.characterReponseIronMan
import com.example.marvelcompose.presentation.activity.view.MainViewModelTestMock.characterReponseSpider
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val useCase = mock<CharactersUseCase>()
    private val viewModel = MainViewModel(useCase)

    private val pagingDataCharacters = PagingData.from(
        listOf(
            characterReponseSpider,
            characterReponseIronMan
        )
    )

    @ExperimentalCoroutinesApi
    @Test
    fun `should validate the paging data object values when calling charactersPagingData`() =
        runBlocking {
            whenever(useCase.getCharacters("")).doReturn(flowOf(pagingDataCharacters))
            val result = viewModel.getAllCharacters("")
            assertEquals(1, result.count())
        }
}
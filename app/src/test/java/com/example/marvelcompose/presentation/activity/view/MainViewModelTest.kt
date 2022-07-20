package com.example.marvelcompose.presentation.activity.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import com.example.marvelcompose.MainCoroutineRule
import com.example.marvelcompose.domain.usecase.CharactersUseCase
import com.example.marvelcompose.presentation.activity.view.MainViewModelTestMock.characterResponseIronMan
import com.example.marvelcompose.presentation.activity.view.MainViewModelTestMock.characterResponseSpider
import com.example.marvelcompose.presentation.activity.view.MainViewModelTestMock.pagingDataComics
import com.example.marvelcompose.presentation.activity.view.MainViewModelTestMock.pagingDataSeries
import com.example.marvelcompose.presentation.activity.view.MainViewModelTestMock.pagingDataStories
import com.example.marvelcompose.presentation.activity.view.model.CharacterDetails
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val useCase = mock<CharactersUseCase>()
    private val characterObserver = mock<Observer<CharacterDetails>>()
    private val loadingObserver = mock<Observer<Boolean>>()
    private lateinit var viewModel: MainViewModel

    private val pagingDataCharacters = PagingData.from(
        listOf(
            characterResponseSpider,
            characterResponseIronMan
        )
    )

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        viewModel = MainViewModel(useCase)
        viewModel.characterDetails.observeForever(characterObserver)
        viewModel.loading.observeForever(loadingObserver)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should validate the paging data object values when calling charactersPagingData`() =
        runBlocking {
            whenever(useCase.getCharacters("")).doReturn(flowOf(pagingDataCharacters))
            val result = viewModel.getAllCharacters("")
            assertEquals(1, result.count())
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `when observe live data, then call get character details by id, then return character detail`() =
        runBlocking {
            val comicsFlow = flowOf(pagingDataComics)
            val seriesFlow = flowOf(pagingDataSeries)
            val storiesFlow = flowOf(pagingDataStories)
            whenever(useCase.getCharacterById(1)).doReturn(listOf(characterResponseSpider))
            whenever(useCase.getComics(1)).doReturn(comicsFlow)
            whenever(useCase.getSeries(1)).doReturn(seriesFlow)
            whenever(useCase.getEvents(1)).doReturn(storiesFlow)
            viewModel.getCharacterDetailsById(1)
            verify(loadingObserver).onChanged(true)
            verify(characterObserver).onChanged(
                CharacterDetails(
                    listOf(characterResponseSpider),
                    comicsFlow,
                    seriesFlow,
                    storiesFlow
                )
            )
            verify(loadingObserver).onChanged(false)
        }
}
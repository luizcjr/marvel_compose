package com.example.marvelcompose.domain.usecase

import com.example.marvelcompose.data.repository.CharacterRepository
import com.example.marvelcompose.domain.usecase.CharactersUseCaseImplTestMock.comicsDataWrapperResponse
import com.example.marvelcompose.domain.usecase.CharactersUseCaseImplTestMock.dataWrapperResponse
import com.example.marvelcompose.domain.usecase.CharactersUseCaseImplTestMock.seriesDataWrapperResponse
import com.example.marvelcompose.domain.usecase.CharactersUseCaseImplTestMock.storiesDataWrapperResponse
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okio.IOException
import org.junit.Assert.assertNotNull
import org.junit.Assert.fail
import org.junit.Test
import org.mockito.exceptions.base.MockitoException

class CharactersUseCaseImplTest {

    private val repository = mock<CharacterRepository>()
    private val useCase = CharactersUseCaseImpl(repository)

    @Test
    fun `given response, when call get characters, then return paging data`() = runBlocking {
        whenever(repository.fetchCharacters(mapOf(Pair("", "")))).doReturn(dataWrapperResponse)
        val result = useCase.getCharacters("")
        assertNotNull(result.first())
    }

    @Test
    fun `given response, when call get characters by id, then return character`() = runBlocking {
        whenever(repository.fetchCharacterById(1)).doReturn(dataWrapperResponse)
        val result = useCase.getCharacterById(1)
        assertNotNull(result.first())
    }

    @Test(expected = MockitoException::class)
    fun `given failure, when call get characters by id, then return exception`() = runBlocking {
        whenever(repository.fetchCharacterById(1)).doThrow(IOException())
        fail()
    }

    @Test
    fun `given response, when call get comics, then return paging data`() = runBlocking {
        whenever(repository.fetchComicsByCharacterId(mapOf(Pair("", "")), 1)).doReturn(
            comicsDataWrapperResponse
        )
        val result = useCase.getComics(1)
        assertNotNull(result.first())
    }

    @Test
    fun `given response, when call get series, then return paging data`() = runBlocking {
        whenever(repository.fetchSeriesByCharacterId(mapOf(Pair("", "")), 1)).doReturn(
            seriesDataWrapperResponse
        )
        val result = useCase.getSeries(1)
        assertNotNull(result.first())
    }

    @Test
    fun `given response, when call get stories, then return paging data`() = runBlocking {
        whenever(repository.fetchEventsByCharacterId(mapOf(Pair("", "")), 1)).doReturn(
            storiesDataWrapperResponse
        )
        val result = useCase.getEvents(1)
        assertNotNull(result.first())
    }
}
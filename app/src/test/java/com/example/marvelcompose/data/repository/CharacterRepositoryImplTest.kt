package com.example.marvelcompose.data.repository

import com.example.marvelcompose.data.network.service.CharacterService
import com.example.marvelcompose.data.repository.CharacterRepositoryImplTestMock.comicsDataWrapperResponse
import com.example.marvelcompose.data.repository.CharacterRepositoryImplTestMock.dataWrapperResponse
import com.example.marvelcompose.data.repository.CharacterRepositoryImplTestMock.seriesDataWrapperResponse
import com.example.marvelcompose.data.repository.CharacterRepositoryImplTestMock.storiesDataWrapperResponse
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

class CharacterRepositoryImplTest {

    private val service = mock<CharacterService>()
    private val repository = CharacterRepositoryImpl(service)

    @Test
    fun `given repository, when call getCharacter, then return data`() = runBlocking {
        whenever(service.getCharacters(mapOf(Pair("", "")))).doReturn(dataWrapperResponse)
        val result = repository.fetchCharacters(mapOf(Pair("", "")))
        assertEquals(dataWrapperResponse, result)
    }

    @Test
    fun `given repository, when call getCharacterById, then return data`() = runBlocking {
        whenever(service.getCharacterById(1)).doReturn(dataWrapperResponse)
        val result = repository.fetchCharacterById(1)
        assertEquals(dataWrapperResponse, result)
    }

    @Test
    fun `given repository, when call getComicsByCharacterId, then return data`() = runBlocking {
        whenever(service.getComicsByCharacterId(1, mapOf(Pair("", "")))).doReturn(
            comicsDataWrapperResponse
        )
        val result = repository.fetchComicsByCharacterId(mapOf(Pair("", "")), 1)
        assertEquals(comicsDataWrapperResponse, result)
    }

    @Test
    fun `given repository, when call getSeriesByCharacterId, then return data`() = runBlocking {
        whenever(service.getSeriesByCharacterId(1, mapOf(Pair("", "")))).doReturn(
            seriesDataWrapperResponse
        )
        val result = repository.fetchSeriesByCharacterId(mapOf(Pair("", "")), 1)
        assertEquals(seriesDataWrapperResponse, result)
    }

    @Test
    fun `given repository, when call getStoriesByCharacterId, then return data`() = runBlocking {
        whenever(service.getEventsByCharacterId(1, mapOf(Pair("", "")))).doReturn(
            storiesDataWrapperResponse
        )
        val result = repository.fetchEventsByCharacterId(mapOf(Pair("", "")), 1)
        assertEquals(storiesDataWrapperResponse, result)
    }
}
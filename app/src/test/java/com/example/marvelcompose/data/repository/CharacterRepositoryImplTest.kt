package com.example.marvelcompose.data.repository

import com.example.marvelcompose.data.network.service.CharacterService
import com.example.marvelcompose.data.repository.CharacterRepositoryImplTestMock.dataWrapperResponse
import com.nhaarman.mockitokotlin2.any
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
}
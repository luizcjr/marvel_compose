package com.example.marvelcompose.domain.usecase

import com.example.marvelcompose.data.repository.CharacterRepository
import com.example.marvelcompose.domain.usecase.CharactersUseCaseImplTestMock.dataWrapperResponse
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
}
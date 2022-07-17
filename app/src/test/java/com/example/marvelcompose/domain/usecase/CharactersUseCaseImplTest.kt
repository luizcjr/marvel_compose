package com.example.marvelcompose.domain.usecase

import com.example.marvelcompose.data.repository.CharacterRepository
import com.example.marvelcompose.domain.usecase.CharactersUseCaseImplTestMock.dataWrapperResponse
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Test

class CharactersUseCaseImplTest {

    private val repository = mock<CharacterRepository>()
    private val useCase = CharactersUseCaseImpl(repository)

    @Test
    fun `given response, when call get characters, then return paging data`() = runBlocking {
        whenever(repository.fetchCharacters(mapOf(Pair("", "")))).doReturn(dataWrapperResponse)
        val result = useCase.getCharacters("")
        assertNotNull(result.first())
    }
}
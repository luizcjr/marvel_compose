package com.example.marvelcompose.domain.di

import com.example.marvelcompose.domain.usecase.CharactersUseCase
import com.example.marvelcompose.domain.usecase.CharactersUseCaseImpl
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModules {

    fun load() {
        loadKoinModules(useCaseModule())
    }

    private fun useCaseModule(): Module {
        return module {
            single<CharactersUseCase> { CharactersUseCaseImpl(get()) }
        }
    }
}
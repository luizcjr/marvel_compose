package com.example.marvelcompose.presentation.application

import android.app.Application
import com.example.marvelcompose.data.di.DataModules
import com.example.marvelcompose.domain.di.DomainModules
import com.example.marvelcompose.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MarvelApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MarvelApplication)
            androidLogger()
            DataModules.load()
            DomainModules.load()
            PresentationModule.load()
        }
    }
}
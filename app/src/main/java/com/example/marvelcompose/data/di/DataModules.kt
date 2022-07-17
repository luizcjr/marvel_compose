package com.example.marvelcompose.data.di

import android.icu.util.Calendar
import android.icu.util.TimeZone
import com.example.marvelcompose.data.network.interceptor.AuthorizationInterceptor
import com.example.marvelcompose.data.network.service.CharacterService
import com.example.marvelcompose.data.repository.CharacterRepository
import com.example.marvelcompose.data.repository.CharacterRepositoryImpl
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DataModules {
    private const val connectionTimeoutSeconds = 20 * 1000L
    private const val url = "https://gateway.marvel.com/v1/public/"
    private const val PUBLIC_KEY = "6f93152a87ee295cbf0974456cc198b6"
    private const val PRIVATE_KEY = "8b5aee742f2f86c9619d31169a808c4919608fe1"

    fun load() {
        loadKoinModules(repositoryModule() + networkModule())
    }

    private fun repositoryModule(): Module {
        return module {
            single<CharacterRepository> { CharacterRepositoryImpl(get()) }
        }
    }

    private fun networkModule(): Module {
        return module {
            single<CharacterService> { createService(get()) }

            single {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

                val okHttpBuilder = OkHttpClient.Builder()
                okHttpBuilder.readTimeout(connectionTimeoutSeconds, TimeUnit.SECONDS)
                okHttpBuilder.writeTimeout(connectionTimeoutSeconds, TimeUnit.SECONDS)
                okHttpBuilder.callTimeout(connectionTimeoutSeconds, TimeUnit.SECONDS)
                okHttpBuilder.connectTimeout(connectionTimeoutSeconds, TimeUnit.SECONDS)
                okHttpBuilder.addInterceptor(loggingInterceptor)
                okHttpBuilder.addInterceptor(
                    AuthorizationInterceptor(
                        publicKey = PUBLIC_KEY,
                        privateKey = PRIVATE_KEY,
                        calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                    )
                )
                if (BuildConfig.DEBUG) {
                    okHttpBuilder.addInterceptor(OkHttpProfilerInterceptor())
                }
                okHttpBuilder.build()
            }
        }
    }

    private inline fun <reified T> createService(
        client: OkHttpClient
    ): T {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(T::class.java)
    }
}
package com.rsshool2021.android.finaltask.easyquizy.data.common.module

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.rsshool2021.android.finaltask.easyquizy.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(contentType: MediaType, json: Json): Retrofit =
        Retrofit.Builder().apply {
            addConverterFactory(json.asConverterFactory(contentType))
            baseUrl(Constants.BASE_URL)
        }.build()

    @Provides
    fun provideContentType(): MediaType = "application/json".toMediaType()

    @Provides
    fun provideJson(): Json = Json { ignoreUnknownKeys = true }
}
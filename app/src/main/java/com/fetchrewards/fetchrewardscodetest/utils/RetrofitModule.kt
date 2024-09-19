package com.fetchrewards.fetchrewardscodetest.utils

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Hilt module that provides the Retrofit instance for dependency injection.
 */
@InstallIn(SingletonComponent::class)
@Module
object RetrofitModule {

    /**
     * Provides the base URL for the Retrofit instance.
     */
    @Provides
    fun provideBaseUrl() = "https://fetch-hiring.s3.amazonaws.com/"

    /**
     * Provides the Retrofit instance with the base URL and Gson converter factory.
     */
    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * Provides the API service for dependency injection.
     */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): APIService = retrofit.create(APIService::class.java)

}
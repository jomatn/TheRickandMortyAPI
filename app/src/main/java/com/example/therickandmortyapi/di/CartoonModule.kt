package com.example.therickandmortyapi.di

import com.example.therickandmortyapi.data.api.CartoonApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CartoonModule {

    private const val TIMEOUT_DURATION = 10L

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()


    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): CartoonApiService =
        retrofit.create(CartoonApiService::class.java)

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(TIMEOUT_DURATION, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_DURATION, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_DURATION, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
}
package com.ramo.newsapp.di

import com.ramo.newsapp.domain.repository.NewsRepository
import com.ramo.newsapp.domain.usecase.ChangeFavoriteUseCase
import com.ramo.newsapp.domain.usecase.GetFavoritesUseCase
import com.ramo.newsapp.domain.usecase.GetNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetNewsUseCase(newsRepository: NewsRepository): GetNewsUseCase =
        GetNewsUseCase(newsRepository)

    @Provides
    @Singleton
    fun provideGetFavoritesUseCase(newsRepository: NewsRepository): GetFavoritesUseCase =
        GetFavoritesUseCase(newsRepository)

    @Provides
    @Singleton
    fun provideChangeFavoriteUseCase(newsRepository: NewsRepository): ChangeFavoriteUseCase =
        ChangeFavoriteUseCase(newsRepository)


}
package com.ramo.newsapp.di

import com.ramo.newsapp.data.repository.FakeRepository
import com.ramo.newsapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val fakeMode = true

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepository(): NewsRepository =
        if (fakeMode) FakeRepository() else FakeRepository()
    // TODO: change real repository else 

}
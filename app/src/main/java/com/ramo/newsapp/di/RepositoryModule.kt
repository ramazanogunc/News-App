package com.ramo.newsapp.di

import com.ramo.newsapp.data.remote.RemoteDataSource
import com.ramo.newsapp.data.repository.FakeRepository
import com.ramo.newsapp.data.repository.NewsRepositoryImpl
import com.ramo.newsapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val fakeMode = false

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepository(remoteDataSource: RemoteDataSource): NewsRepository =
        if (fakeMode) FakeRepository() else NewsRepositoryImpl(remoteDataSource)

}
package com.example.newsapiclient.presentation.di

import com.example.newsapiclient.data.repository.NewsRepositoryImpl
import com.example.newsapiclient.data.repository.datasource.NewsRemoteDataSource
import com.example.newsapiclient.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesNewsRepository(newsRemoteDataSource: NewsRemoteDataSource) : NewsRepository = NewsRepositoryImpl(newsRemoteDataSource)
}
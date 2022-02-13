package com.example.newsapiclient.presentation.di

import com.example.newsapiclient.data.api.NewsService
import com.example.newsapiclient.data.repository.datasource.NewsRemoteDataSource
import com.example.newsapiclient.data.repository.datasourceimpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Singleton
    @Provides
    fun providesRemoteDataSource(newsService: NewsService) : NewsRemoteDataSource = NewsRemoteDataSourceImpl(newsService)
}
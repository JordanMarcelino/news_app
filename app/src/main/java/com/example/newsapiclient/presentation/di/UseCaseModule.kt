package com.example.newsapiclient.presentation.di

import com.example.newsapiclient.domain.repository.NewsRepository
import com.example.newsapiclient.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun providesGetNewsHeadlinesUseCase(newsRepository: NewsRepository) : GetNewsHeadlinesUseCase = GetNewsHeadlinesUseCase(newsRepository)

    @Singleton
    @Provides
    fun providesSearchedNewsUseCase(newsRepository: NewsRepository) : SearchedNewsUseCase = SearchedNewsUseCase(newsRepository)

    @Singleton
    @Provides
    fun providesSaveNewsUseCase(newsRepository: NewsRepository) : SavedNewsUseCase = SavedNewsUseCase(newsRepository)

    @Singleton
    @Provides
    fun providesDeleteNewsUseCase(newsRepository: NewsRepository) : DeleteSavedNewsUseCase = DeleteSavedNewsUseCase(newsRepository)

    @Singleton
    @Provides
    fun providesGetSavedNewsUseCase(newsRepository: NewsRepository) : GetSavedNewsUseCase = GetSavedNewsUseCase(newsRepository)
}
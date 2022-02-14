package com.example.newsapiclient.presentation.di

import android.app.Application
import com.example.newsapiclient.domain.usecase.*
import com.example.newsapiclient.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun providesNewsViewModelFactory(
        app : Application,
        getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
        searchedNewsUseCase: SearchedNewsUseCase,
        savedNewsUseCase: SavedNewsUseCase,
        deleteSavedNewsUseCase: DeleteSavedNewsUseCase,
        getSavedNewsUseCase: GetSavedNewsUseCase
    ) : NewsViewModelFactory = NewsViewModelFactory(app, getNewsHeadlinesUseCase, searchedNewsUseCase, savedNewsUseCase, deleteSavedNewsUseCase, getSavedNewsUseCase)
}
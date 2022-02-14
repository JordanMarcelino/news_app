package com.example.newsapiclient.presentation.di

import com.example.newsapiclient.data.db.ArticleDao
import com.example.newsapiclient.data.repository.datasource.NewsLocalDataSource
import com.example.newsapiclient.data.repository.datasourceimpl.NewsLocalDataSourceImpl
import com.example.newsapiclient.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun providesNewsLocalDataSource(articleDao: ArticleDao) : NewsLocalDataSource = NewsLocalDataSourceImpl(articleDao)
}
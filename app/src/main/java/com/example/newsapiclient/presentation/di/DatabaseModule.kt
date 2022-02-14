package com.example.newsapiclient.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.newsapiclient.data.db.ArticleDao
import com.example.newsapiclient.data.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun providesNewsDatabase(application: Application): NewsDatabase = Room.databaseBuilder(
        application,
        NewsDatabase::class.java,
        "news_db"
    ).fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun providesArticleDao(newsDatabase: NewsDatabase) : ArticleDao = newsDatabase.getArticleDao()
}
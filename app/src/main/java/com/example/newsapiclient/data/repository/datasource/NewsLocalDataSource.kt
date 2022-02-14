package com.example.newsapiclient.data.repository.datasource

import com.example.newsapiclient.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {
    suspend fun saveArticleToDB(article: Article)
    suspend fun deleteArticleFromDB(article: Article)
    fun getAllArticlesFromDB() : Flow<List<Article>>
}
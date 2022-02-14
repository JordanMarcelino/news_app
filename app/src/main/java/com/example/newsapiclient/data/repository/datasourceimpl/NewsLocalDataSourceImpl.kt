package com.example.newsapiclient.data.repository.datasourceimpl

import com.example.newsapiclient.data.db.ArticleDao
import com.example.newsapiclient.data.model.Article
import com.example.newsapiclient.data.repository.datasource.NewsLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class NewsLocalDataSourceImpl(
    private val articleDao: ArticleDao
) : NewsLocalDataSource {
    override suspend fun saveArticleToDB(article: Article){
            articleDao.insert(article)
    }
    override suspend fun deleteArticleFromDB(article: Article) {
            articleDao.delete(article)
    }

    override fun getAllArticlesFromDB(): Flow<List<Article>> = articleDao.getArticle()
}
package com.example.newsapiclient.data.repository

import com.example.newsapiclient.data.model.APIResponse
import com.example.newsapiclient.data.model.Article
import com.example.newsapiclient.data.repository.datasource.NewsLocalDataSource
import com.example.newsapiclient.data.repository.datasource.NewsRemoteDataSource
import com.example.newsapiclient.data.util.Resource
import com.example.newsapiclient.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
) : NewsRepository {
    override suspend fun getNewsHeadline(country: String, page: Int): Resource<APIResponse> =
        responseToResource(newsRemoteDataSource.getNewsHeadlines(country, page))

    override suspend fun getSearchedNews(
        country: String,
        page: Int,
        searchQuery: String
    ): Resource<APIResponse> =
        responseToResource(newsRemoteDataSource.getSearchedNews(country, page, searchQuery))

    private fun responseToResource(response: Response<APIResponse>): Resource<APIResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun saveNews(article: Article) = newsLocalDataSource.saveArticleToDB(article)


    override suspend fun deleteNews(article: Article) = newsLocalDataSource.deleteArticleFromDB(article)

    override fun getSavedNews(): Flow<List<Article>> = newsLocalDataSource.getAllArticlesFromDB()
}
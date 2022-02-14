package com.example.newsapiclient.data.repository.datasourceimpl

import com.example.newsapiclient.data.api.NewsService
import com.example.newsapiclient.data.model.APIResponse
import com.example.newsapiclient.data.repository.datasource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsService: NewsService
) : NewsRemoteDataSource {
    override suspend fun getNewsHeadlines(country: String, page: Int): Response<APIResponse> =
        newsService.getNewsHeadlines(country, page)

    override suspend fun getSearchedNews(
        country: String,
        page: Int,
        searchQuery: String
    ): Response<APIResponse> =
        newsService.getSearchedNes(country, page, searchQuery)

}
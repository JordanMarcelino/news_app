package com.example.newsapiclient.data.repository.datasource

import com.example.newsapiclient.data.model.APIResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getNewsHeadlines(country : String, page : Int) : Response<APIResponse>
    suspend fun getSearchedNews(country : String, page : Int, searchQuery : String) : Response<APIResponse>
}
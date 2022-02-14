package com.example.newsapiclient.domain.usecase

import com.example.newsapiclient.domain.repository.NewsRepository

class SearchedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(country : String, page : Int, searchQuery : String) = newsRepository.getSearchedNews(country, page, searchQuery)
}
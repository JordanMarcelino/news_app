package com.example.newsapiclient.domain.usecase

import com.example.newsapiclient.domain.repository.NewsRepository

class SearchedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(searchQuery : String) = newsRepository.getSearchedNews(searchQuery)
}
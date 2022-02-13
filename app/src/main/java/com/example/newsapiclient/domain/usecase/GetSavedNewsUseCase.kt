package com.example.newsapiclient.domain.usecase

import com.example.newsapiclient.domain.repository.NewsRepository

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute() = newsRepository.getSavedNews()
}
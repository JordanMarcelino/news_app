package com.example.newsapiclient.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapiclient.domain.usecase.*


class NewsViewModelFactory(
    private val app : Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val searchedNewsUseCase: SearchedNewsUseCase,
    private val savedNewsUseCase: SavedNewsUseCase,
    private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(app, getNewsHeadlinesUseCase, searchedNewsUseCase, savedNewsUseCase, deleteSavedNewsUseCase, getSavedNewsUseCase) as T
    }
}
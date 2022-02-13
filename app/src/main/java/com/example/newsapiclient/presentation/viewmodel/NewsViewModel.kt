package com.example.newsapiclient.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsapiclient.data.model.APIResponse
import com.example.newsapiclient.data.util.Resource
import com.example.newsapiclient.domain.usecase.GetNewsHeadlinesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


class NewsViewModel(
    private val app: Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase
) : AndroidViewModel(app) {

    private val newsHeadlines = MutableLiveData<Resource<APIResponse>>()
    val newsHeadlinesData: LiveData<Resource<APIResponse>>
        get() = newsHeadlines

    fun getNewsHeadlines(country: String, page: Int) = viewModelScope.launch(Dispatchers.IO) {
        newsHeadlines.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {
                val response = getNewsHeadlinesUseCase.execute(country, page)
                newsHeadlines.postValue(response)
            } else {
                newsHeadlines.postValue(Resource.Error("Network is not available"))
            }
        } catch (e: Exception) {
            newsHeadlines.postValue(Resource.Error(e.message.toString()))
        }
    }

    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val cap = cm.getNetworkCapabilities(cm.activeNetwork)
            if (cap != null) {
                return when {
                    cap.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    cap.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    cap.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
        } else {
            val activeNetworkInfo = cm.activeNetwork
            if (activeNetworkInfo != null) {
                return true
            }
        }
        return false
    }
}
package com.grjt.hackernews.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.grjt.hackernews.core.Resource
import com.grjt.hackernews.data.model.NewsModel
import com.grjt.hackernews.domain.GetNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    val newsUseCase = GetNewsUseCase()

    fun getNews() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(newsUseCase()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

}
package com.grjt.hackernews.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grjt.hackernews.data.model.NewsModel
import com.grjt.hackernews.domain.GetNewsUseCase
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    val newsUseCase = GetNewsUseCase()
    val newsModel = MutableLiveData<List<NewsModel>>()

    fun getNews() {
        viewModelScope.launch {
            val currentNews = newsUseCase()
            newsModel.postValue(currentNews)
        }
    }

}
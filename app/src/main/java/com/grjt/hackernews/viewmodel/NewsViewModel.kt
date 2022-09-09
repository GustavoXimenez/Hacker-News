package com.grjt.hackernews.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grjt.hackernews.model.NewsModel

class NewsViewModel : ViewModel() {

    val newsModel = MutableLiveData<NewsModel>()

    fun getNews() {
        val currentNews = NewsModel()
        newsModel.postValue(currentNews)
    }

}
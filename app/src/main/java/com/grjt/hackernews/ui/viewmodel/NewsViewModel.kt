package com.grjt.hackernews.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.grjt.hackernews.core.Resource
import com.grjt.hackernews.domain.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsUseCase: GetNewsUseCase
) : ViewModel() {
    fun getNews() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(newsUseCase()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}
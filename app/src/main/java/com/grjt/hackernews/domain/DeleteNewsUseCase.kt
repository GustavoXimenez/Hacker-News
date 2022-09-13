package com.grjt.hackernews.domain

import com.grjt.hackernews.data.NewsRepository
import com.grjt.hackernews.data.database.entities.toDatabase
import com.grjt.hackernews.domain.model.News
import javax.inject.Inject

class DeleteNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(news: News) {
        repository.updateNews(news)
    }
}
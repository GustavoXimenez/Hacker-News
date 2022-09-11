package com.grjt.hackernews.domain

import com.grjt.hackernews.data.NewsRepository
import com.grjt.hackernews.data.database.entities.toDatabase
import com.grjt.hackernews.data.model.NewsModel
import com.grjt.hackernews.domain.model.News
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(): ArrayList<News> {
        val news = repository.getAllNewsFromApi()

        return if(news.isNotEmpty()){
            repository.clearNews()
            repository.insertNews(ArrayList(news.map { it.toDatabase() }))
            news
        } else {
            repository.getAllNewsFromDatabase()
        }

    }
}
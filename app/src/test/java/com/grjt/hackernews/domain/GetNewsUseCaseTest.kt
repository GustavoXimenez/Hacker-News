package com.grjt.hackernews.domain

import com.grjt.hackernews.data.NewsRepository
import com.grjt.hackernews.domain.model.News
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetNewsUseCaseTest {

    @RelaxedMockK
    private lateinit var newsRepository: NewsRepository

    lateinit var getNewsUseCase: GetNewsUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getNewsUseCase = GetNewsUseCase(newsRepository)
    }

    @Test
    fun `when the api doesnt return anything then get values from database`() = runBlocking {
        //Given
        coEvery { newsRepository.getAllNewsFromApi() } returns ArrayList()
        //When
        getNewsUseCase()
        //Then
        coVerify(exactly = 1) { newsRepository.getAllNewsFromDatabase() }
    }

    @Test
    fun `when the api return then get values from api`() = runBlocking {
        //Given
        val myList = ArrayList(listOf(
            News("hoy", "GustavoXimenez", 1,
                "Hola Mundo", "https://google.com", false),
            News("ayer", "GustavoXimenez", 2,
                "Hola Mundo Android", "https://google.com", false)))

        coEvery { newsRepository.getAllNewsFromApi() } returns myList
        //When
        val response = getNewsUseCase()
        //Then
        coVerify(exactly = 1) { newsRepository.clearNews() }
        coVerify(exactly = 1) { newsRepository.insertNews(any()) }
        coVerify(exactly = 0) { newsRepository.getAllNewsFromDatabase() }
        assert(myList == response)
    }
}
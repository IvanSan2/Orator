package com.ivansan.newsapp.service

import com.ivansan.newsapp.service.dto.NewsResponse
import com.ivansan.newsapp.utils.MyCustomRetryHandler
import com.lembergsolutions.retrofitretry.api.RetryOnError
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @RetryOnError(maxRetries = 24, handlerClass = MyCustomRetryHandler::class) //recall 24 times in interval one hour
    @GET("api/1/news")
    suspend fun getNews(
        @Query("language") lang: String? = null,
        @Query("image") img: String? = "1",
        @Query("page") nextPage: String? = null
    ): NewsResponse


    @RetryOnError(maxRetries = 24, handlerClass = MyCustomRetryHandler::class) //recall 24 times in interval one hour
    @GET("api/1/news")
    suspend fun getNewsBySearch(
        @Query("q") param: String? = null,
        @Query("image") img: String? = "1",
        @Query("page") nextPage: String? = null
    ): NewsResponse

}
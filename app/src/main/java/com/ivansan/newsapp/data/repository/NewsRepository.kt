package com.ivansan.newsapp.data.repository

import android.content.SharedPreferences
import com.ivansan.newsapp.data.dao.ResultDao
import com.ivansan.newsapp.data.mapper.toResult
import com.ivansan.newsapp.service.NewsService
import com.ivansan.newsapp.ui.settings.PREFERENCES_LANGUAGE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsDao: ResultDao, private val newsService: NewsService, private val preferences: SharedPreferences) {

    private val languages = mapOf(
        Pair(null,"All"),Pair("en","english"),Pair("he","hebrew"),
        Pair("ar","arabic"),Pair("ru","russian")
    )

    private val usersLanguage = preferences.getString(PREFERENCES_LANGUAGE,null)

    fun getNews() = newsDao.getNews()
    fun getNewsByLanguage(s: String? = languages[usersLanguage]) = newsDao.getNewsByLanguage(s)
    fun getTopNews() = newsDao.getTopNews()
    fun getSportNews() = newsDao.getSportNews()
    fun getBusinessNews() = newsDao.getBusinessNews()
    fun getEntertainmentNews() = newsDao.getEntertainmentNews()
    fun getHeathNews() = newsDao.getHealthNews()
    fun getTechnologyNews() = newsDao.getTechnologyNews()
    fun getFoodNews() = newsDao.getFoodNews()
    fun getPoliticsNews() = newsDao.getPoliticsNews()
    fun getWorldNews() = newsDao.getWorldNews()
    fun getFavoriteNews() = newsDao.getFavoriteNews()
    fun getTopNewsByLanguage(s: String? = languages[usersLanguage]) = newsDao.getTopNewsByLanguage(s)
    fun getSportNewsByLanguage(s: String? = languages[usersLanguage]) = newsDao.getSportNewsByLanguage(s)
    fun getBusinessNewsByLanguage(s: String? = languages[usersLanguage]) = newsDao.getBusinessNewsByLanguage(s)
    fun getEntertainmentNewsByLanguage(s: String? = languages[usersLanguage]) = newsDao.getEntertainmentNewsByLanguage(s)
    fun getHeathNewsByLanguage(s: String? = languages[usersLanguage]) = newsDao.getHealthNewsByLanguage(s)
    fun getTechnologyNewsByLanguage(s: String? = languages[usersLanguage]) = newsDao.getTechnologyNewsByLanguage(s)
    fun getFoodNewsByLanguage(s: String? = languages[usersLanguage]) = newsDao.getFoodNewsByLanguage(s)
    fun getPoliticsNewsByLanguage(s: String? = languages[usersLanguage]) = newsDao.getPoliticsNewsByLanguage(s)
    fun getWorldNewsByLanguage(s: String? = languages[usersLanguage]) = newsDao.getWorldNewsByLanguage(s)

    suspend fun refreshNews(){
        withContext(Dispatchers.IO){

            var newsResponse = newsService.getNews(
                lang = preferences.getString(
                PREFERENCES_LANGUAGE,null)
            )
            var nextPage = newsResponse.nextPage

            for (i in 0..5) {

                newsDao.add(newsResponse.results.map {
                    it.toResult()
                })
                newsResponse = newsService.getNews(
                    lang = preferences.getString(
                        PREFERENCES_LANGUAGE,null),
                    nextPage = nextPage)
                nextPage = newsResponse.nextPage
            }
        }
    }
}
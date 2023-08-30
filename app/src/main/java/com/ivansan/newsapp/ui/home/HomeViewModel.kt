package com.ivansan.newsapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivansan.newsapp.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: NewsRepository) : ViewModel()  {

    init {
        viewModelScope.launch {
            //refresh from the api:
            repo.refreshNews()
        }
    }

    val news = repo.getNews()
    val newsByLanguage = repo.getNewsByLanguage()
    val topNews = repo.getTopNews()
    val sportNews = repo.getSportNews()
    val businessNews = repo.getBusinessNews()
    val entertainmentNews = repo.getEntertainmentNews()
    val heathNews = repo.getHeathNews()
    val technologyNews = repo.getTechnologyNews()
    val foodNews = repo.getFoodNews()
    val politicsNews = repo.getPoliticsNews()
    val worldNews = repo.getWorldNews()
    val topNewsByLanguage = repo.getTopNewsByLanguage()
    val sportNewsByLanguage = repo.getSportNewsByLanguage()
    val businessNewsByLanguage = repo.getBusinessNewsByLanguage()
    val entertainmentNewsByLanguage = repo.getEntertainmentNewsByLanguage()
    val heathNewsByLanguage = repo.getHeathNewsByLanguage()
    val technologyNewsByLanguage = repo.getTechnologyNewsByLanguage()
    val foodNewsByLanguage = repo.getFoodNewsByLanguage()
    val politicsNewsByLanguage = repo.getPoliticsNewsByLanguage()
    val worldNewsByLanguage = repo.getWorldNewsByLanguage()
}
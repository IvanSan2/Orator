package com.ivansan.newsapp.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivansan.newsapp.service.NewsService
import com.ivansan.newsapp.service.dto.ResultDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val newsService: NewsService): ViewModel() {

    private val _searchNews: MutableLiveData<List<ResultDTO>> = MutableLiveData()
    val searchNews: LiveData<List<ResultDTO>> = _searchNews


    fun searchNews(query: String?){
        query?.let{
            if(query.isEmpty()) {
                _searchNews.value = listOf()
                return
            }
            viewModelScope.launch {
                try {
                    val newsResponse = newsService.getNewsBySearch(param = '"' + it + '"')
                    _searchNews.value = newsResponse.results
                } catch (e: RuntimeException){
                    Log.d("ErrorCatch",e.message.toString())
                }
            }

        }
    }

}
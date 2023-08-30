package com.ivansan.newsapp.ui.composable


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ivansan.newsapp.data.dao.ResultDao
import com.ivansan.newsapp.data.entity.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel@Inject constructor(private val newsDao: ResultDao):ViewModel() {



    private val _sharedItem: MutableLiveData<Result> = MutableLiveData()
    val sharedItem: MutableLiveData<Result> = _sharedItem



    fun addResult(newResult: Result){
        _sharedItem.value = newResult
    }

    fun addToFavorite(result:Result){
        _sharedItem.value?.favorite  = true
        newsDao.addToFavorites(result)
    }

    fun removeFromFavorite(result:Result){
        _sharedItem.value?.favorite = false
        newsDao.addToFavorites(result)
    }

}
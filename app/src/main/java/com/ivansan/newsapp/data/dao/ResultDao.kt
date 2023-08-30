package com.ivansan.newsapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ivansan.newsapp.data.entity.Result

@Dao
interface ResultDao {



    // REPLACE = Update db from cloud
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(news:List<Result>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToFavorites(result:Result)


    @Query("SELECT * FROM news WHERE favorite = 1 ORDER BY pubDate DESC")
    fun getFavoriteNews(): LiveData<List<Result>>

    //@Query("SELECT * FROM news ORDER BY id DESC")
    @Query("SELECT * FROM news ORDER BY pubDate DESC")
    fun getNews(): LiveData<List<Result>>

    @Query("SELECT * FROM news WHERE language LIKE :usersLang ORDER BY pubDate DESC")
    fun getNewsByLanguage(usersLang: String?): LiveData<List<Result>>

    @Query("SELECT * FROM news WHERE category LIKE '%top%' ORDER BY pubDate DESC")
    fun getTopNews(): LiveData<List<Result>>

    @Query("SELECT * FROM news WHERE language LIKE :usersLang AND category LIKE '%top%' ORDER BY pubDate DESC")
    fun getTopNewsByLanguage(usersLang: String?): LiveData<List<Result>>

    @Query("SELECT * FROM news WHERE category LIKE '%sport%' ORDER BY pubDate DESC")
    fun getSportNews(): LiveData<List<Result>>

    @Query("SELECT * FROM news WHERE language LIKE :usersLang AND category LIKE '%sport%' ORDER BY pubDate DESC")
    fun getSportNewsByLanguage(usersLang: String?): LiveData<List<Result>>

    @Query("SELECT * FROM news WHERE category LIKE '%business%' ORDER BY pubDate DESC")
    fun getBusinessNews(): LiveData<List<Result>>

    @Query("SELECT * FROM news WHERE language LIKE :usersLang AND category LIKE '%business%' ORDER BY pubDate DESC")
    fun getBusinessNewsByLanguage(usersLang: String?): LiveData<List<Result>>

    @Query("SELECT * FROM news WHERE category LIKE '%entertainment%' ORDER BY pubDate DESC")
    fun getEntertainmentNews(): LiveData<List<Result>>

    @Query("SELECT * FROM news WHERE language LIKE :usersLang AND category LIKE '%entertainment%' ORDER BY pubDate DESC")
    fun getEntertainmentNewsByLanguage(usersLang: String?): LiveData<List<Result>>

    @Query("SELECT * FROM news WHERE category LIKE '%health%' ORDER BY pubDate DESC")
    fun getHealthNews(): LiveData<List<Result>>

    @Query("SELECT * FROM news WHERE language LIKE :usersLang AND category LIKE '%health%' ORDER BY pubDate DESC")
    fun getHealthNewsByLanguage(usersLang: String?): LiveData<List<Result>>

    @Query("SELECT * FROM news WHERE category LIKE '%technology%' ORDER BY pubDate DESC")
    fun getTechnologyNews(): LiveData<List<Result>>

    @Query("SELECT * FROM news WHERE language LIKE :usersLang AND category LIKE '%technology%' ORDER BY pubDate DESC")
    fun getTechnologyNewsByLanguage(usersLang: String?): LiveData<List<Result>>

    @Query("SELECT * FROM news WHERE category LIKE '%food%' ORDER BY pubDate DESC")
    fun getFoodNews(): LiveData<List<Result>>

    @Query("SELECT * FROM news WHERE language LIKE :usersLang AND category LIKE '%food%' ORDER BY pubDate DESC")
    fun getFoodNewsByLanguage(usersLang: String?): LiveData<List<Result>>

    @Query("SELECT * FROM news WHERE category LIKE '%politics%' ORDER BY pubDate DESC")
    fun getPoliticsNews(): LiveData<List<Result>>

    @Query("SELECT * FROM news WHERE language LIKE :usersLang AND category LIKE '%politics%' ORDER BY pubDate DESC")
    fun getPoliticsNewsByLanguage(usersLang: String?): LiveData<List<Result>>

    @Query("SELECT * FROM news WHERE category LIKE '%world%' ORDER BY pubDate DESC")
    fun getWorldNews(): LiveData<List<Result>>

    @Query("SELECT * FROM news WHERE language LIKE :usersLang AND category LIKE '%world%' ORDER BY pubDate DESC")
    fun getWorldNewsByLanguage(usersLang: String?): LiveData<List<Result>>
}
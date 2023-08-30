package com.ivansan.newsapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ivansan.newsapp.data.converters.ResultConverters
import com.ivansan.newsapp.data.dao.ResultDao
import com.ivansan.newsapp.data.entity.Result

const val DB_VERSION = 1
const val DB_NAME = "AppDatabase"

@TypeConverters(ResultConverters::class)
@Database(entities = [Result::class], version = DB_VERSION)
abstract class AppDatabase: RoomDatabase() {

    abstract fun resultDao(): ResultDao


}
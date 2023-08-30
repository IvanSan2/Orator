package com.ivansan.newsapp.data.converters

import androidx.room.TypeConverter

fun <T> List<T>.toShortString() = joinToString(separator = ",") { "$it" }

class ResultConverters {

    @TypeConverter
    fun listToString(list:List<String>?):String?{
        return list?.toShortString()
    }

    @TypeConverter
    fun stringToList(string: String?):List<String>?{
        return string?.split(",")
    }
}
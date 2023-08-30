package com.ivansan.newsapp.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "news")
class Result(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val category: List<String>? = listOf(""),
    val content: String? = "",
    val country: List<String>? = listOf(""),
    val creator: List<String>? = listOf(""),
    val description: String? = "",
    val imageUrl: String? = "",
    val keywords: List<String>? = listOf(""),
    val language: String? = "",
    val link: String,
    val pubDate: String,
    val sourceId: String? = "",
    val title: String,
    val videoUrl: String? = "",
    var favorite: Boolean = false
): Parcelable


package com.ivansan.newsapp.service.dto


import com.google.gson.annotations.SerializedName

data class ResultDTO(
    val category: List<String>? = listOf(""),
    val content: String? = "",
    val country: List<String>? = listOf(""),
    val creator: List<String>? = listOf(""),
    val description: String? = "",
    @SerializedName("image_url")
    val imageUrl: String? = "",
    val keywords: List<String>? = listOf(""),
    val language: String? = "",
    val link: String,
    val pubDate: String,
    @SerializedName("source_id")
    val sourceId: String? = "",
    val title: String,
    @SerializedName("video_url")
    val videoUrl: String? = ""
)
package com.ivansan.newsapp.service.dto

data class ErrorResponse(
    val code: String,
    val message: String,
    val status: String
)
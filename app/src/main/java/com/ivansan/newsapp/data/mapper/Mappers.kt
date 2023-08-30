package com.ivansan.newsapp.data.mapper

import com.ivansan.newsapp.data.entity.Result
import com.ivansan.newsapp.service.dto.ResultDTO


fun ResultDTO.toResult() = Result(
    category = category,
    content = content,
    country = country,
    creator = creator,
    description = description,
    imageUrl = imageUrl,
    keywords = keywords,
    language = language,
    link = link,
    pubDate = pubDate,
    sourceId = sourceId,
    title = title,
    videoUrl = videoUrl,
    id = title.hashCode()
)
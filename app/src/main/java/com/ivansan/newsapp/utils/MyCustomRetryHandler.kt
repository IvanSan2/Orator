package com.ivansan.newsapp.utils

import com.lembergsolutions.retrofitretry.api.RetryHandler
import okhttp3.Request
import retrofit2.Response

class MyCustomRetryHandler: RetryHandler {
    /**
     * Get the delay to wait before retrying next request
     * @param request request object
     * @param result request result containing response or error
     * @param retryCount current retry count
     * @param maxRetries maximum retries set in annotation
     * @return >= 0 delay before retry or -1 to cancel retrying
     */
    override fun getRetryDelay(request: Request, result: Result<retrofit2.Response<out Any?>>, retryCount: Int, maxRetries: Int): Long{
        return 3600000
    }
}
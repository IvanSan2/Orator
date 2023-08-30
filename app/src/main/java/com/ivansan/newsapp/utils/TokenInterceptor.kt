package com.ivansan.newsapp.utils


import android.content.SharedPreferences
import com.ivansan.newsapp.ui.settings.PREFERENCES_API_KEY
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(private val preferences: SharedPreferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        //the original request:
        val original =
            chain.request()

        val url = original.url.newBuilder()
            .addQueryParameter("apikey", preferences.getString(PREFERENCES_API_KEY,""))
            .build()

        val request = original.newBuilder().url(url).build()

        return chain.proceed(request)
    }
}
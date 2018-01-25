package com.orcchg.musicsquare.data.remote

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Adds http headers to every network request.
 */
class RequestHeaderInterceptor(private val accessToken: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
                .header("Authorization", "Basic $accessToken")
                .build()
        return chain.proceed(request)
    }
}

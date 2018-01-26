package com.orcchg.musicsquare.data.remote

import com.orcchg.musicsquare.domain.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RestAdapter {

    companion object {
        const val ENDPOINT = "https://api.github.com/"
    }

    @GET("user/{userId}")
    fun user(@Path("userId") userId: Int): Call<User>

    @GET("users")
    fun users(): Call<List<User>>
}

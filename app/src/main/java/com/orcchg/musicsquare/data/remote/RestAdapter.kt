package com.orcchg.musicsquare.data.remote

import com.orcchg.musicsquare.domain.User
import retrofit2.Call
import retrofit2.http.GET

interface RestAdapter {

    companion object {
        const val ENDPOINT = "https://api.github.com/"
    }

    @GET("users")
    fun users(): Call<List<User>>
}

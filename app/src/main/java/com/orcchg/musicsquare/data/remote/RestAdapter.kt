package com.orcchg.musicsquare.data.remote

import com.orcchg.musicsquare.domain.User
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface RestAdapter {

    companion object {
        const val ENDPOINT = "https://api.github.com/"
    }

    @GET("user/{userId}")
    fun user(@Path("userId") userId: Int): Flowable<User>

    @GET("users")
    fun users(): Flowable<List<User>>
}

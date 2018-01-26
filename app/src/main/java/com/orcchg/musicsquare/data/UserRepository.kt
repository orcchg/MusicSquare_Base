package com.orcchg.musicsquare.data

import com.orcchg.musicsquare.data.remote.RestAdapter
import com.orcchg.musicsquare.domain.User
import retrofit2.Callback

class UserRepository(val cloud: RestAdapter) {

    fun user(userId: Int, cb: Callback<User>) {
        cloud.user(userId).enqueue(cb)
    }

    fun users(cb: Callback<List<User>>) {
        cloud.users().enqueue(cb)
    }
}

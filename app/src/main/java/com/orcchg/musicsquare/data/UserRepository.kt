package com.orcchg.musicsquare.data

import com.orcchg.musicsquare.data.remote.RestAdapter
import com.orcchg.musicsquare.domain.User
import retrofit2.Callback

class UserRepository(val cloud: RestAdapter) {

    fun users(cb: Callback<List<User>>) {
        cloud.users().enqueue(cb)
    }
}

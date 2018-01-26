package com.orcchg.musicsquare.data

import com.orcchg.musicsquare.data.local.UserDao
import com.orcchg.musicsquare.data.remote.RestAdapter
import com.orcchg.musicsquare.domain.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class UserRepository(val cloud: RestAdapter, val local: UserDao) {

    fun user(userId: Int, l: (user: User) -> Unit) {
        l.invoke(local.user(userId))
    }

    fun users(l: (users: List<User>) -> Unit) =
        if (local.totalUsers() > 0) {
            l.invoke(local.users())
        } else {
            cloud.users().enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    val users = response.body()
                    if (users != null) {
                        local.addUsers(users)
                        l.invoke(local.users())
                    } else {
                        Timber.e("Failed to get users")
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    Timber.e(t, "Network error")
                }
            })
        }
}

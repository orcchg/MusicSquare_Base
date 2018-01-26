package com.orcchg.musicsquare.data

import com.orcchg.musicsquare.data.local.UserDao
import com.orcchg.musicsquare.data.remote.RestAdapter
import com.orcchg.musicsquare.domain.User
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class UserRepository(val cloud: RestAdapter, val local: UserDao) {

    fun user(userId: Int) = local.user(userId)

    fun users(): Flowable<List<User>> =
        local.totalUsers()
                .flatMap {
                    val net = if (it > 0) Flowable.empty<List<User>>()
                              else cloud.users()
                                        .doOnNext(local::addUsers)
                                        .doOnError(Timber::e)

                    Flowable.concatArrayEager(local.users(), net)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
}

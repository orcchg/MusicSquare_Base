package com.orcchg.musicsquare.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.orcchg.musicsquare.domain.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "Users.db"

        fun build(applicationContext: Context) =
                Room.databaseBuilder(applicationContext, UserDatabase::class.java, DATABASE_NAME)
                        .build()
    }

    abstract fun userDao(): UserDao
}

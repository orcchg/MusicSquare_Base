package com.orcchg.musicsquare.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.orcchg.musicsquare.domain.User

@Dao
interface UserDao {

    @Query("SELECT * FROM ${User.TABLE_NAME} WHERE ${User.COLUMN_ID} = :arg0")
    fun user(id: Int): User

    @Query("SELECT * FROM ${User.TABLE_NAME}")
    fun users(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUsers(users: List<User>)

    @Query("SELECT COUNT(*) FROM ${User.TABLE_NAME}")
    fun totalUsers(): Int
}

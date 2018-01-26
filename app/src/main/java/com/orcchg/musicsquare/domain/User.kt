package com.orcchg.musicsquare.domain

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.orcchg.musicsquare.domain.User.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class User(@PrimaryKey @ColumnInfo(name = COLUMN_ID) var id: Int = 0,
                @ColumnInfo(name = COLUMN_LOGIN) var login: String = "",
                @ColumnInfo(name = COLUMN_NAME) var name: String = "",
                @ColumnInfo(name = COLUMN_AVATAR_URL) var avatar_url: String = "") {

    companion object {
        const val TABLE_NAME = "users"

        const val COLUMN_ID = "id"
        const val COLUMN_LOGIN = "login"
        const val COLUMN_NAME = "name"
        const val COLUMN_AVATAR_URL = "avatar_url"
    }
}

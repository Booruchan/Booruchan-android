package com.makentoshe.booruchan.library.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.makentoshe.booruchan.library.database.dao.DatabaseActionSearchHistoryDao
import com.makentoshe.booruchan.library.database.entity.DatabaseActionSearchHistory

@Database(
    entities = [DatabaseActionSearchHistory::class],
    version = 1,
)
abstract class HistoryDatabase: RoomDatabase() {
    abstract fun actionHistoryDao(): DatabaseActionSearchHistoryDao
}
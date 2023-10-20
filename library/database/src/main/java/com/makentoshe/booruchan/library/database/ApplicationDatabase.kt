package com.makentoshe.booruchan.library.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.makentoshe.booruchan.library.database.dao.DatabaseTagDao
import com.makentoshe.booruchan.library.database.entity.DatabaseTag

@Database(
    entities = [
        DatabaseTag::class,
    ],
    version = 2,
)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun tagDao(): DatabaseTagDao
}

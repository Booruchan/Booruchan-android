package com.makentoshe.booruchan.library.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.makentoshe.booruchan.library.database.dao.DatabasePostDao
import com.makentoshe.booruchan.library.database.dao.DatabaseTagDao
import com.makentoshe.booruchan.library.database.entity.DatabasePost
import com.makentoshe.booruchan.library.database.entity.DatabaseTag

@Database(
    entities = [
        DatabaseTag::class,
        DatabasePost::class,
    ],
    version = 5,
)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun tagDao(): DatabaseTagDao

    abstract fun postDao(): DatabasePostDao
}

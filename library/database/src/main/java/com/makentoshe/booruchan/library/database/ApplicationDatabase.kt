package com.makentoshe.booruchan.library.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.makentoshe.booruchan.library.database.dao.DatabasePostDao
import com.makentoshe.booruchan.library.database.dao.DatabaseTagDao
import com.makentoshe.booruchan.library.database.dao.DatabaseTagPostCrossRefDao
import com.makentoshe.booruchan.library.database.entity.DatabasePost
import com.makentoshe.booruchan.library.database.entity.DatabaseTag
import com.makentoshe.booruchan.library.database.entity.DatabaseTagPostCrossRef

@Database(
    entities = [
        DatabaseTag::class,
        DatabasePost::class,
        DatabaseTagPostCrossRef::class,
    ],
    version = 10,
)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun tagDao(): DatabaseTagDao

    abstract fun postTagCrossRefDao(): DatabaseTagPostCrossRefDao

    abstract fun postDao(): DatabasePostDao
}

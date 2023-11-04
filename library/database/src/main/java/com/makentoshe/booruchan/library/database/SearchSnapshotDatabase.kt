package com.makentoshe.booruchan.library.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.makentoshe.booruchan.library.database.dao.DatabaseSearchSnapshotDao
import com.makentoshe.booruchan.library.database.entity.DatabaseSearchSnapshot

@Database(
    entities = [DatabaseSearchSnapshot::class],
    version = 5,
)
abstract class SearchSnapshotDatabase : RoomDatabase() {

    abstract fun sourceSearchSnapshotDao(): DatabaseSearchSnapshotDao
}

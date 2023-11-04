package com.makentoshe.booruchan.library.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.makentoshe.booruchan.library.database.entity.DatabaseSearchSnapshot
import kotlinx.coroutines.flow.Flow

@Dao
interface DatabaseSearchSnapshotDao {
    @Query("SELECT * FROM DatabaseSearchSnapshot")
    fun getAll(): List<DatabaseSearchSnapshot>

    @Query("SELECT * FROM DatabaseSearchSnapshot")
    fun flowAll(): Flow<List<DatabaseSearchSnapshot>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(navigation: DatabaseSearchSnapshot)

    @Delete
    fun delete(navigation: DatabaseSearchSnapshot)

    @Query("DELETE FROM DatabaseSearchSnapshot")
    fun clear()
}
package com.makentoshe.booruchan.library.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.makentoshe.booruchan.library.database.entity.DatabaseTagPostCrossRef

@Dao
interface DatabaseTagPostCrossRefDao {
    @Query("SELECT * FROM DatabaseTagPostCrossRef")
    fun getAll(): List<DatabaseTagPostCrossRef>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tag: DatabaseTagPostCrossRef)

    @Delete
    fun delete(tag: DatabaseTagPostCrossRef)

    @Query("DELETE FROM DatabaseTagPostCrossRef")
    fun clear()
}
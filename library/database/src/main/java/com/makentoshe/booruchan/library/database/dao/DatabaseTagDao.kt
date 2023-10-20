package com.makentoshe.booruchan.library.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.makentoshe.booruchan.library.database.entity.DatabaseTag

@Dao
interface DatabaseTagDao {
    @Query("SELECT * FROM DatabaseTag")
    fun getAll(): List<DatabaseTag>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tag: DatabaseTag)

    @Delete
    fun delete(tag: DatabaseTag)

    @Query("DELETE FROM DatabaseTag")
    fun clear()
}
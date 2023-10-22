package com.makentoshe.booruchan.library.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.makentoshe.booruchan.library.database.entity.DatabasePost

@Dao
interface DatabasePostDao {
    @Query("SELECT * FROM DatabasePost")
    fun getAll(): List<DatabasePost>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tag: DatabasePost)

    @Delete
    fun delete(tag: DatabasePost)

    @Query("DELETE FROM DatabasePost")
    fun clear()
}

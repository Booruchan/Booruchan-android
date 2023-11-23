package com.makentoshe.booruchan.library.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.makentoshe.booruchan.library.database.entity.DatabaseActionSearchHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface DatabaseActionSearchHistoryDao {
    @Query("SELECT * FROM DatabaseActionSearchHistory")
    fun getAll(): List<DatabaseActionSearchHistory>

    @Query("SELECT * FROM DatabaseActionSearchHistory")
    fun flowAll(): Flow<List<DatabaseActionSearchHistory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(navigation: DatabaseActionSearchHistory)

    @Delete
    fun delete(navigation: DatabaseActionSearchHistory)

    @Query("DELETE FROM DatabaseActionSearchHistory")
    fun clear()
}
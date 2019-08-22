package com.example.android104.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserRecordDao {
    @Query("SELECT * FROM userRecords ORDER BY id ASC")
    fun getAll(): List<UserRecord>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(record: UserRecord)

    @Query("DELETE FROM userRecords WHERE id = :path")
    fun deleteByPath(path: Int)

    @Query("SELECT COUNT(id) FROM userRecords")
    fun count(): Int

    @Query("DELETE FROM userRecords")
    fun clear()
}
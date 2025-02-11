package com.iptvsource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iptvsource.database.entities.ChannelEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChannelDao {

    @Query("SELECT * FROM channels")
    fun getAllChannels(): Flow<List<ChannelEntity>> // ✅ שימוש ב-Flow

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChannels(channels: List<ChannelEntity>) // ✅ הכנסת רשימת ערוצים

    @Query("DELETE FROM channels")
    suspend fun deleteAll() // ✅ מחיקת ערוצים
}

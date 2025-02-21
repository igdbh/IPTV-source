package com.iptvsource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iptvsource.database.entities.ChannelEntity

@Dao
interface ChannelDao {
    @Query("SELECT * FROM channels")
    fun getAllChannels(): List<ChannelEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChannel(channel: ChannelEntity)

    @Query("DELETE FROM channels")
    fun deleteAll()
}

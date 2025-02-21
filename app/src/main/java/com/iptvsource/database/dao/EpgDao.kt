package com.iptvsource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iptvsource.database.entities.EpgEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EpgDao {
    @Query("SELECT * FROM epg WHERE channelId = :channelId ORDER BY startTime ASC")
    fun getEpgForChannel(channelId: Int): Flow<List<EpgEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(epgList: List<EpgEntity>)

    @Query("DELETE FROM epg WHERE endTime < :currentTime")
    suspend fun clearOldEpg(currentTime: Long)
}

package com.iptvsource.database.repository

import com.iptvsource.database.dao.ChannelDao
import com.iptvsource.database.entities.ChannelEntity
import kotlinx.coroutines.flow.Flow
import android.util.Log

class ChannelRepository(private val channelDao: ChannelDao) {

    fun getAllChannels(): Flow<List<ChannelEntity>> = channelDao.getAllChannels()

    suspend fun insertChannel(channel: ChannelEntity) {
        try {
            channelDao.insertChannels(listOf(channel))
            Log.d("ChannelRepository", "Inserted channel: ${channel.name}")
        } catch (e: Exception) {
            Log.e("ChannelRepository", "Error inserting channel", e)
        }
    }

    suspend fun deleteAllChannels() {
        channelDao.deleteAll()
    }
}

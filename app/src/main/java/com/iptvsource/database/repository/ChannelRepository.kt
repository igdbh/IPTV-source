package com.iptvsource.database.repository

import com.iptvsource.database.dao.ChannelDao
import com.iptvsource.database.entities.ChannelEntity
import kotlinx.coroutines.flow.Flow

class ChannelRepository(private val channelDao: ChannelDao) {

    fun getAllChannels(): Flow<List<ChannelEntity>> {
        return channelDao.getAllChannels()
    }

    suspend fun insertChannel(channel: ChannelEntity) {
        channelDao.insert(channel)
    }

    suspend fun deleteAll() {
        channelDao.deleteAll()
    }

    fun getVisibleChannels(): Flow<List<ChannelEntity>> {
        return channelDao.getVisibleChannels()
    }

    suspend fun hideChannel(channelId: Int, isHidden: Boolean) {
        channelDao.hideChannel(channelId, isHidden)
    }
}

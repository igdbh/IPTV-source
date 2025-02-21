package com.iptvsource.database.repository

import com.iptvsource.database.dao.EpgDao
import com.iptvsource.database.entities.EpgEntity
import kotlinx.coroutines.flow.Flow

class EpgRepository(private val epgDao: EpgDao) {

    fun getEpgForChannel(channelId: Int): Flow<List<EpgEntity>> {
        return epgDao.getEpgForChannel(channelId)
    }

    suspend fun updateEpg(epgList: List<EpgEntity>) {
        epgDao.insertAll(epgList)
    }

    suspend fun clearOldEpg(currentTime: Long) {
        epgDao.clearOldEpg(currentTime)
    }
}

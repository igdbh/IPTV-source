package com.iptvsource.database.repository

import com.iptvsource.database.dao.VodDao
import com.iptvsource.database.entities.VodEntity
import kotlinx.coroutines.flow.Flow

class VodRepository(private val vodDao: VodDao) {

    fun getAllVod(): Flow<List<VodEntity>> {
        return vodDao.getAllVod()
    }

    suspend fun insertVod(vod: VodEntity) {
        vodDao.insert(vod)
    }

    suspend fun deleteAll() {
        vodDao.deleteAll()
    }

    fun getVisibleVods(): Flow<List<VodEntity>> {
        return vodDao.getVisibleVods()
    }

    suspend fun hideVod(vodId: Int, isHidden: Boolean) {
        vodDao.hideVod(vodId, isHidden)
    }
}

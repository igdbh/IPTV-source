package com.iptvsource.database.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iptvsource.database.entities.EpgEntity
import com.iptvsource.database.repository.EpgRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class EpgViewModel(private val epgRepository: EpgRepository) : ViewModel() {

    fun getEpgForChannel(channelId: Int): Flow<List<EpgEntity>> {
        return epgRepository.getEpgForChannel(channelId)
    }

    fun refreshEpg(epgList: List<EpgEntity>) {
        viewModelScope.launch {
            epgRepository.updateEpg(epgList)
        }
    }

    fun clearOldEpg() {
        viewModelScope.launch {
            epgRepository.clearOldEpg(System.currentTimeMillis())
        }
    }
}

package com.iptvsource.database.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iptvsource.database.repository.ChannelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import com.iptvsource.database.entities.ChannelEntity

class ChannelViewModel(private val channelRepository: ChannelRepository) : ViewModel() {

    val visibleChannels: Flow<List<ChannelEntity>> = channelRepository.getVisibleChannels()

    fun hideChannel(channelId: Int, isHidden: Boolean) {
        viewModelScope.launch {
            channelRepository.hideChannel(channelId, isHidden)
        }
    }

    fun insertChannel(channel: ChannelEntity) {
        viewModelScope.launch {
            channelRepository.insertChannel(channel)
        }
    }

    fun clearChannels() {
        viewModelScope.launch {
            channelRepository.deleteAll()
        }
    }
}

package com.iptvsource.database.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.iptvsource.database.entities.ChannelEntity
import com.iptvsource.database.repository.ChannelRepository
import com.iptvsource.database.repository.XtreamCodesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ChannelViewModel(
    private val channelRepository: ChannelRepository,
    private val xtreamRepository: XtreamCodesRepository
) : ViewModel() {

    val allChannels: Flow<List<ChannelEntity>> = channelRepository.getAllChannels()

    fun fetchLiveStreams(username: String, password: String) {
        viewModelScope.launch {
            try {
                xtreamRepository.fetchAndSaveLiveStreams(username, password)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun insertChannel(channel: ChannelEntity) {
        viewModelScope.launch {
            channelRepository.insert(channel)
        }
    }

    fun clearChannels() {
        viewModelScope.launch {
            channelRepository.clearAll()
        }
    }
}

class ChannelViewModelFactory(
    private val channelRepository: ChannelRepository,
    private val xtreamRepository: XtreamCodesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChannelViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChannelViewModel(channelRepository, xtreamRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

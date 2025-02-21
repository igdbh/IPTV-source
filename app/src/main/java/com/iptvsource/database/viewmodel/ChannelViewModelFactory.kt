package com.iptvsource.database.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iptvsource.database.repository.ChannelRepository

class ChannelViewModelFactory(private val channelRepository: ChannelRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChannelViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChannelViewModel(channelRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

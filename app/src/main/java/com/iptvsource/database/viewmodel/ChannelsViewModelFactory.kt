package com.iptvsource.database.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iptvsource.database.repository.ChannelRepository
import com.iptvsource.database.repository.XtreamCodesRepository

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

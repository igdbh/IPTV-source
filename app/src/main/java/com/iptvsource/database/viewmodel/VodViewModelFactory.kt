package com.iptvsource.database.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iptvsource.database.repository.VodRepository
import com.iptvsource.database.repository.XtreamCodesRepository


class VodViewModelFactory(
    private val vodRepository: VodRepository,
    private val xtreamRepository: XtreamCodesRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VodViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VodViewModel(vodRepository, xtreamRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

package com.iptvsource.database.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iptvsource.database.entities.VodEntity
import com.iptvsource.database.repository.VodRepository
import com.iptvsource.database.repository.XtreamCodesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class VodViewModel(
    private val repository: VodRepository,
    private val xtreamRepository: XtreamCodesRepository  // ⬅️ נוספה התמיכה ב-Xtream Codes API
) : ViewModel() {

    val allVod: Flow<List<VodEntity>> = repository.getAllVod()

    fun insertVod(vodList: List<VodEntity>) {
        viewModelScope.launch {
            repository.insertVod(vodList)
        }
    }

    fun clearAllVod() {
        viewModelScope.launch {
            repository.clearAllVod()
        }
    }

    fun fetchVod(username: String, password: String) {
        viewModelScope.launch {
            xtreamRepository.fetchVod(username, password)  // ⬅️ עכשיו אין שגיאה כאן
        }
    }
}

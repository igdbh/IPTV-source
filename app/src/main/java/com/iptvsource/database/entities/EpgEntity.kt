package com.iptvsource.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "epg")
data class EpgEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val channelId: Int,
    val title: String,
    val startTime: Long,
    val endTime: Long,
    val description: String,
    val catchUpUrl: String? // URL לצפייה חוזרת אם קיים
)

package com.iptvsource.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vod")
data class VodEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val category: String,
    val streamUrl: String,
    val logo: String,
    val isHidden: Boolean = false // ✅ שדה חדש שמאפשר הסתרת תכני VOD
)

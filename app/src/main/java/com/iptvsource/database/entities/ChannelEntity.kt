package com.iptvsource.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "channels")
data class ChannelEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,       // שם הערוץ
    val logo: String,       // כתובת URL של לוגו הערוץ
    val category: String,   // קטגוריה של הערוץ
    val streamUrl: String,  // כתובת URL להזרמת המדיה
    val isHidden: Boolean = false // ✅ שדה חדש שמאפשר הסתרת ערוצים
)

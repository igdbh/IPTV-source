package com.iptvsource.database.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.iptvsource.database.dao.ChannelDao
import com.iptvsource.database.dao.VodDao
import com.iptvsource.database.entities.ChannelEntity
import com.iptvsource.database.entities.VodEntity

@Database(entities = [ChannelEntity::class, VodEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun channelDao(): ChannelDao
    abstract fun vodDao(): VodDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "iptv_database"
                )
                    .fallbackToDestructiveMigration() // ✅ הוספת הרס אוטומטי במקרה של שינוי גרסה
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

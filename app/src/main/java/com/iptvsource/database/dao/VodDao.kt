package com.iptvsource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iptvsource.database.entities.VodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VodDao {

    @Query("SELECT * FROM vod WHERE isHidden = 0") // ✅ מחזיר רק תכנים שלא מוסתרים
    fun getVisibleVod(): Flow<List<VodEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVod(vodList: List<VodEntity>)

    @Query("DELETE FROM vod")
    suspend fun deleteAllVod()

    @Query("UPDATE vod SET isHidden = :isHidden WHERE id = :vodId") // ✅ פונקציה לעדכון הסתרה
    suspend fun hideVod(vodId: Int, isHidden: Boolean)
}

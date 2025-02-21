package com.iptvsource.database.repository

import android.util.Log
import com.iptvsource.database.network.XtreamCodesApiService
import com.iptvsource.database.entities.ChannelEntity
import com.iptvsource.database.entities.VodEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class XtreamCodesRepository(
    private val apiService: XtreamCodesApiService,
    private val channelRepository: ChannelRepository,
    private val vodRepository: VodRepository
) {
    suspend fun fetchAndSaveLiveStreams(username: String, password: String) {
        withContext(Dispatchers.IO) {
            try {
                Log.d("XtreamCodesRepository", "📡 Fetching live streams from API...")

                val response = apiService.getLiveStreams(username, password)

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        val channels = body.liveStreams?.map {
                            ChannelEntity(
                                id = it.streamId ?: 0,
                                name = it.name ?: "Unknown",
                                logo = it.logo ?: "",
                                category = it.categoryName ?: "Uncategorized",
                                streamUrl = it.streamUrl ?: ""
                            )
                        } ?: emptyList()

                        Log.d("XtreamCodesRepository", "✅ API returned ${channels.size} channels")
                        channelRepository.insertChannels(channels)
                    } else {
                        Log.e("XtreamCodesRepository", "❌ API response body is null!")
                    }
                } else {
                    Log.e("XtreamCodesRepository", "❌ API error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("XtreamCodesRepository", "❌ Error fetching live streams", e)
            }
        }
    }

    suspend fun fetchVod(username: String, password: String) {
        withContext(Dispatchers.IO) {
            try {
                Log.d("XtreamCodesRepository", "📡 Fetching VOD from API...")

                val response = apiService.getVodStreams(username, password)

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        val vodList = body.map {
                            VodEntity(
                                name = it.name ?: "Unknown",
                                category = it.categoryName ?: "Uncategorized",
                                streamUrl = it.streamUrl ?: "",
                                logo = it.streamIcon ?: ""
                            )
                        }

                        Log.d("XtreamCodesRepository", "✅ API returned ${vodList.size} VOD items")
                        vodRepository.insertVod(vodList)
                    } else {
                        Log.e("XtreamCodesRepository", "❌ API response body is null!")
                    }
                } else {
                    Log.e("XtreamCodesRepository", "❌ API error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("XtreamCodesRepository", "❌ Error fetching VOD", e)
            }
        }
    }
}

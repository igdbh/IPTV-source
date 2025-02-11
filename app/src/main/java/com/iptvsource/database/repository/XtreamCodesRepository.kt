package com.iptvsource.database.repository

import android.util.Log
import com.iptvsource.database.entities.ChannelEntity
import com.iptvsource.database.network.XtreamCodesApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class XtreamCodesRepository(
    private val apiService: XtreamCodesApiService,
    private val channelRepository: ChannelRepository
) {

    suspend fun fetchAndSaveLiveStreams(username: String, password: String) {
        withContext(Dispatchers.IO) {
            try {
                Log.d("XtreamCodesRepository", "Fetching live streams from API...")

                val response = apiService.getLiveStreams(username, password)

                if (response.isSuccessful) {
                    val channels = response.body()?.map {
                        ChannelEntity(
                            id = it.streamId,
                            name = it.name,
                            logo = it.logo,
                            category = it.categoryName,
                            url = it.streamUrl
                        )
                    } ?: emptyList()

                    channelRepository.insertChannels(channels)
                    Log.d("XtreamCodesRepository", "Live streams fetched and saved successfully")
                } else {
                    Log.e("XtreamCodesRepository", "Error fetching live streams: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("XtreamCodesRepository", "Error fetching live streams", e)
            }
        }
    }
}

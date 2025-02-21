package com.iptvsource.database.network

import android.util.Log
import com.iptvsource.database.network.models.XtreamLiveStreamResponse
import com.iptvsource.database.network.models.XtreamVodResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface XtreamCodesApiService {

    @GET("player_api.php?action=get_live_streams")
    suspend fun getLiveStreams(
        @Query("username") username: String,
        @Query("password") password: String
    ): Response<XtreamLiveStreamResponse>

    @GET("player_api.php?action=get_vod_streams")
    suspend fun getVodStreams(
        @Query("username") username: String,
        @Query("password") password: String
    ): Response<List<XtreamVodResponse>>

    companion object {
        fun create(baseUrl: String): XtreamCodesApiService {
            Log.d("XtreamCodesApiService", "📡 חיבור לשרת: $baseUrl")

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(XtreamCodesApiService::class.java)
        }
    }
}

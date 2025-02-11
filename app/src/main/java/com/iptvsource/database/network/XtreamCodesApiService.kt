package com.iptvsource.database.network

import android.util.Log
import com.iptvsource.database.network.models.XtreamLiveStreamResponse
import com.iptvsource.database.network.models.XtreamUserInfoResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface XtreamCodesApiService {

    @GET("player_api.php?action=get_live_streams")
    suspend fun getLiveStreams(
        @Query("username") username: String,
        @Query("password") password: String
    ): XtreamLiveStreamResponse

    companion object {
        fun create(): XtreamCodesApiService {
            val baseUrl = "http://your-xtream-url/"
            Log.d("XtreamCodesApiService", "Base URL: $baseUrl")  // ✅ נוסיף הדפסת URL

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(XtreamCodesApiService::class.java)
        }
    }
}


package com.iptvsource.database.network.models

import com.google.gson.annotations.SerializedName

data class LiveStream(
    @SerializedName("stream_id") val streamId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("stream_icon") val logo: String?, // ✅ שם עדכני ומתוקן
    @SerializedName("category_id") val category: String, // ✅ ווידוא התאמה לנתוני JSON
    @SerializedName("stream_url") val streamUrl: String // ✅ ווידוא קיום שדה URL
)

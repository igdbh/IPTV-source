package com.iptvsource.database.network.models

import com.google.gson.annotations.SerializedName

data class XtreamLiveStreamResponse(
    @SerializedName("live") val liveStreams: List<LiveStream>
)

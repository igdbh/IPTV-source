package com.iptvsource.database.network.models

import com.google.gson.annotations.SerializedName

data class XtreamVodResponse(
    @SerializedName("stream_id") val streamId: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("stream_icon") val streamIcon: String?,
    @SerializedName("category_name") val categoryName: String?,
    @SerializedName("stream_url") val streamUrl: String?
)

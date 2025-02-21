package com.iptvsource.database.network.models

import com.google.gson.annotations.SerializedName

data class LiveStream(
    @SerializedName("stream_id") val streamId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("stream_icon") val logo: String?, // לוודא שזה קיים
    @SerializedName("category_name") val categoryName: String?, // היה category_id, צריך להיות category_name
    @SerializedName("stream_url") val streamUrl: String? // לוודא שזה קיים
)

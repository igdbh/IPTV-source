package com.iptvsource.database.network.models

import com.google.gson.annotations.SerializedName

data class XtreamUserInfoResponse(
    @SerializedName("user_info") val userInfo: XtreamUserInfo
)

data class XtreamUserInfo(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String,
    @SerializedName("status") val status: String,
    @SerializedName("exp_date") val expirationDate: String?,
    @SerializedName("is_trial") val isTrial: String?,
    @SerializedName("active_cons") val activeConnections: Int?,
    @SerializedName("created_at") val createdAt: String?
)

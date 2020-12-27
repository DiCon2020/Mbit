package com.david0926.mbit.data.auth

import okhttp3.MultipartBody

data class UpdateInfoRequest(
    var username: String?,
    var personalityType: String?,
    var topicNotificationStatus: Boolean?,
    var commentNotificationStatus: Boolean?,
    var photo: MultipartBody.Part?,
    var time: Boolean?,
)
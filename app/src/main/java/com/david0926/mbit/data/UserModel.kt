package com.david0926.mbit.data

import com.david0926.mbit.data.notification.Notification
import java.io.Serializable

data class UserModel(
    var _id: String,
    var email: String,
    var password: String,
    var username: String,
    var yearOfBirth: Int,
    var personalityType: String,
    var photo: String,
    var personalityTypeLastDate: String,
    var lastLoginDate: String,
    var createdDate: String,
    var loginCount: Int,
    var notificationList: ArrayList<Notification>,
    var topicNotificationStatus: Boolean,
    var commentNotificationStatus: Boolean,
    var deviceId: String,
    var isDeleted: Boolean,
    var token: String? //  가입할때 토큰과 photo는 Null로 둬도 됩니다


) : Serializable

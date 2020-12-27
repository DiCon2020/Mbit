package com.david0926.mbit.data.notification

import java.io.Serializable

data class Notification(
    val title: String,
    val text: String,
    val date: String
): Serializable


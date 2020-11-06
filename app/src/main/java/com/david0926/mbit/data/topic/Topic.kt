package com.david0926.mbit.data.topic

data class Topic (
    var top: ArrayList<String>,
    var bottom: ArrayList<String>,
    var _id: String,
    var date: String,
    var title: String,
    var topText: String,
    var bottomText: String,
    var __v: Int,
    var click: Int
)
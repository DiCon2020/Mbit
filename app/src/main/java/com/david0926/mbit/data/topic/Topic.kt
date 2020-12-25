package com.david0926.mbit.data.topic

data class Topic (
    var first: ArrayList<String>,
    var second: ArrayList<String>,
    var _id: String,
    var date: String,
    var question: String,
    var firstPercent: Int,
    var secondPercent: Int,
    var firstAnswer: String,
    var secondAnswer: String,
    var __v: Int,
    var click: Int
)
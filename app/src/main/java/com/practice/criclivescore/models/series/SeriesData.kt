package com.practice.criclivescore.models.series

data class SeriesData(
    val apikey: String,
    val data: Data,
    val info: InfoX,
    val status: String
)
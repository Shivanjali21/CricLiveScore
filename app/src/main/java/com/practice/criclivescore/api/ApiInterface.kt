package com.practice.criclivescore.api

import com.practice.criclivescore.models.series.SeriesData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/v1/series_info")
    suspend fun getSeries(@Query("apikey") apikey: String, @Query("id") id: String) : Response<SeriesData>

}
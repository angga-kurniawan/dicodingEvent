package com.example.submissionfundamentalpertama.Api.ApiService

import com.example.submissionfundamentalpertama.Api.DataDicodingEventResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("events")
    suspend fun getEvent(
        @Query("active") active: Int
    ): Response<DataDicodingEventResponse>

    @GET("events")
    suspend fun searchEvent(
        @Query("active") active: Int = -1,
        @Query("q") keyword: String
    ): Response<DataDicodingEventResponse>

    @GET("events")
    suspend fun getDetail(
        @Query("id") getId: Int,
    ): Response<DataDicodingEventResponse>
}
package com.example.submissionfundamentalpertama.Api.ApiClient

import com.example.submissionfundamentalpertama.Api.ApiService.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://event-api.dicoding.dev/"
    val apiService: ApiService by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }
}
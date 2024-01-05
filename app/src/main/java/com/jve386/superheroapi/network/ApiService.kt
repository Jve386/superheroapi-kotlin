package com.jve386.superheroapi.network

import com.jve386.superheroapi.data.SuperHeroDataResponse
import com.jve386.superheroapi.data.SuperHeroDetailResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/{token}/search/{name}")
    suspend fun getSuperheroes(
        @Path("token") apiToken: String,
        @Path("name") superheroName: String
    ): Response<SuperHeroDataResponse>

    @GET("api/{token}/{id}")
    suspend fun getSuperheroDetail(
        @Path("token") apiToken: String,
        @Path("id") superheroId: String
    ): Response<SuperHeroDetailResponse>

    companion object {
        fun create(apiToken: String): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://superheroapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}


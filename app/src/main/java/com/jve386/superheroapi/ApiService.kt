package com.jve386.superheroapi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

// Define an interface for making API requests using Retrofit
interface ApiService {

    // Paste your API Token in here:
    companion object {
        const val API_TOKEN = "AddYourTokenHere"
    }

    // Endpoint to search for superheroes based on their name
    @GET("api/$API_TOKEN/search/{name}")
    suspend fun getSuperheroes(@Path("name") superheroName: String): Response<SuperHeroDataResponse>

    // Endpoint to get detailed information about a specific superhero based on their ID
    @GET("api/$API_TOKEN/{id}")
    suspend fun getSuperheroeDetail(@Path("id") superheroId: String): Response<SuperHeroDetailResponse>

}
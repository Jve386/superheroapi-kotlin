package com.jve386.superheroapi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

// Define an interface for making API requests using Retrofit
interface ApiService {

    // Endpoint to search for superheroes based on their name
    @GET("api/10231637645585664/search/{name}")
    suspend fun getSuperheroes(@Path("name") superheroName: String): Response<SuperHeroDataResponse>

    // Endpoint to get detailed information about a specific superhero based on their ID
    @GET("api/10231637645585664/{id}")
    suspend fun getSuperheroeDetail(@Path("id") superheroId: String): Response<SuperHeroDetailResponse>

}
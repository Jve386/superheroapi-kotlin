package com.jve386.superheroapi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/10231637645585664/search/{name}")
    suspend fun getSuperheroes(@Path("name") superheroName:String):Response<SuperHeroDataResponse>

    @GET("api/10231637645585664/{id}")
    suspend fun getSuperheroeDetail(@Path("id") superheroId:String):Response<SuperHeroDetailResponse>

}
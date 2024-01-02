package com.jve386.superheroapi

import com.google.gson.annotations.SerializedName

// Data class to represent the response from the superhero API
data class SuperHeroDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val superheroes: List<SuperheroItemResponse>
)

// Data class to represent an individual superhero item in the response
data class SuperheroItemResponse(
    @SerializedName("id") val superheroId: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val superheroImage: SuperheroImageResponse
)

// Data class to represent the image information of a superhero
data class SuperheroImageResponse(@SerializedName("url") val url: String)
